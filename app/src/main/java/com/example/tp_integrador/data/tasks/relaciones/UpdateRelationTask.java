package com.example.tp_integrador.data.tasks.relaciones;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Relacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRelationTask extends AsyncTask<Integer, Void, Boolean> {
    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Integer... params) {
        Integer idVoluntario = params[0];
        Integer idProyecto = params[1];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE relaciones SET estado_relacion = false WHERE id_perfil_voluntario = ? AND id_proyecto_ong = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, idVoluntario);
                preparedStatement.setInt(2, idProyecto);

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
