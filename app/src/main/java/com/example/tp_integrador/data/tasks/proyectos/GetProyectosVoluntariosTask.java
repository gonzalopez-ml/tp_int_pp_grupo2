package com.example.tp_integrador.data.tasks.proyectos;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetProyectosVoluntariosTask extends AsyncTask<Integer, Void, List<Proyecto>> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected List<Proyecto> doInBackground(Integer... params) {
        List<Proyecto> projects = new ArrayList<>();
        Integer idVoluntario = params[0];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT distinct Proyectos_ong.*, Perfil_ongs.*, relaciones.estado_relacion " +
                    " FROM Proyectos_ong " +
                    " INNER JOIN Perfil_ongs ON Proyectos_ong.id_perfil_ong = Perfil_ongs.id_perfil_ong " +
                    "                    INNER JOIN relaciones ON Proyectos_ong.id_proyecto = relaciones.id_proyecto_ong " +
                    "                    WHERE EXISTS (" +
                    "                        SELECT 1 " +
                    "                        FROM relaciones  " +
                    "                        WHERE Proyectos_ong.id_proyecto = relaciones.id_proyecto_ong " +
                    "                        AND relaciones.id_perfil_voluntario = ? " +
                    "                    )";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, idVoluntario);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idProject = resultSet.getInt("id_proyecto");
                    String name = resultSet.getString("nombre");
                    String description = resultSet.getString("descripcion");
                    String objetivos = resultSet.getString("necesidades"); //necesidades
                    String disponibilidad = resultSet.getString("disponibilidad");
                    String ubicacion = resultSet.getString("ubicacion");

                    // Llenamos la propiedad ong de la clase Project
                    Ong ong = new Ong();
                    ong.setIdOng(resultSet.getInt("id_perfil_ong"));
                    ong.setName(resultSet.getString("nombre"));
                    ong.setDescription(resultSet.getString("descripcion"));
                    ong.setLogo(resultSet.getString("logo"));
                    ong.setPhone(resultSet.getString("telefono"));
                    ong.setMail(resultSet.getString("mail"));
                    ong.setLocation(resultSet.getString("ubicacion"));

                    Proyecto project = new Proyecto(idProject, ong, name, description, objetivos, ubicacion, disponibilidad);
                    project.setEstadoProyecto(resultSet.getInt("estado_relacion"));
                    projects.add(project);
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    @Override
    protected void onPostExecute(List<Proyecto> projects) {
    }
}
