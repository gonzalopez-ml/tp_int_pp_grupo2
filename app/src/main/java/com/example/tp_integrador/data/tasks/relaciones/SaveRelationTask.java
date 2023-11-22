package com.example.tp_integrador.data.tasks.relaciones;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Relacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveRelationTask extends AsyncTask<Relacion, Void, Boolean> {
    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Relacion... relaciones) {
        Relacion relacion = relaciones[0];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO relaciones (id_perfil_voluntario, id_proyecto_ong, estado_voluntario, estado_relacion) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, relacion.getIdPerfilVoluntario());
                preparedStatement.setInt(2, relacion.getIdProyecto());
                preparedStatement.setString(3, relacion.getEstadoVoluntario().toString());
                preparedStatement.setString(4, relacion.getEstadoRelacion().toString());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
    }
}
