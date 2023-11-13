package com.example.tp_integrador.data.tasks.ongs;

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

public class GetProjectsOngByLocationTask extends AsyncTask<String, Void, List<Proyecto>> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected List<Proyecto> doInBackground(String... strings) {
        String location = strings[0];
        String idVoluntario = strings[1];
        List<Proyecto> projects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * " +
                    "FROM Proyectos_ong " +
                    "INNER JOIN Perfil_ongs ON Proyectos_ong.id_perfil_ong = Perfil_ongs.id_perfil_ong " +
                    "LEFT JOIN Localidades ON Proyectos_ong.ubicacion = Localidades.nombre " +
                    "WHERE NOT EXISTS (" +
                    "    SELECT 1" +
                    "    FROM relaciones" +
                    "    WHERE Proyectos_ong.id_proyecto = relaciones.id_proyecto_ong" +
                    "    AND relaciones.id_perfil_voluntario = ?" +
                    ")" +
                    "AND (LOWER(Localidades.nombre) LIKE LOWER(?) OR Localidades.nombre IS NULL)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, idVoluntario);
                preparedStatement.setString(2, location);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idProject = resultSet.getInt("id_proyecto");
                    String name = resultSet.getString("nombre");
                    String description = resultSet.getString("descripcion");
                    String objetivos = resultSet.getString("necesidades"); //necesidades
                    String disponibilidad = resultSet.getString("disponibilidad");
                    String ubicacion = resultSet.getString("ubicacion");

                    Ong ong = new Ong();
                    ong.setIdOng(resultSet.getInt("id_perfil_ong"));
                    ong.setName(resultSet.getString("nombre"));
                    ong.setDescription(resultSet.getString("descripcion"));
                    ong.setLogo(resultSet.getString("logo"));
                    ong.setPhone(resultSet.getString("telefono"));
                    ong.setMail(resultSet.getString("mail"));
                    ong.setLocation(resultSet.getString("ubicacion"));

                    Proyecto project = new Proyecto(idProject, ong, name, description, objetivos, ubicacion, disponibilidad);
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