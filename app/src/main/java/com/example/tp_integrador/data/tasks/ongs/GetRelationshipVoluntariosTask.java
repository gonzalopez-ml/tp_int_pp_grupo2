package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.data.domain.Voluntario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetRelationshipVoluntariosTask extends AsyncTask<Integer, Void, List<ProyectoVoluntario>> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected List<ProyectoVoluntario> doInBackground(Integer... params) {
        int idOng = params[0];
        List<ProyectoVoluntario> proyectoVoluntarioPairs = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT p.*, v.*, r.* " +
                    "FROM Proyectos_ong p " +
                    "INNER JOIN relaciones r ON p.id_proyecto = r.id_proyecto_ong " +
                    "INNER JOIN Perfil_voluntarios v ON r.id_perfil_voluntario = v.id_perfil_voluntario " +
                    "WHERE p.id_perfil_ong = ? AND r.estado_relacion = 1";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, idOng);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Proyecto proyecto = new Proyecto();
                        Voluntario voluntario = new Voluntario();

                        proyecto.setIdProyecto(resultSet.getInt("p.id_proyecto"));
                        proyecto.setNombre(resultSet.getString("p.nombre"));
                        proyecto.setDescripcion(resultSet.getString("p.descripcion"));
                        proyecto.setObjetivos(resultSet.getString("p.necesidades"));
                        proyecto.setDisponibilidad(resultSet.getString("p.disponibilidad"));
                        proyecto.setUbicacion(resultSet.getString("p.ubicacion"));

                        voluntario.setIdVoluntario(resultSet.getInt("v.id_perfil_voluntario"));
                        voluntario.setName(resultSet.getString("v.nombre"));
                        voluntario.setLastName(resultSet.getString("v.apellido"));
                        voluntario.setDni(resultSet.getString("v.dni"));
                        voluntario.setPhone(resultSet.getString("v.telefono"));
                        voluntario.setSkills(resultSet.getString("v.habilidades"));
                        voluntario.setAvailability(resultSet.getString("v.disponibilidad"));
                        voluntario.setCv(resultSet.getString("v.curriculum"));

                        ProyectoVoluntario pair = new ProyectoVoluntario(proyecto, voluntario);
                        proyectoVoluntarioPairs.add(pair);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyectoVoluntarioPairs;
    }

    @Override
    protected void onPostExecute(List<ProyectoVoluntario> proyectoVoluntarioPairs) {
    }


}
