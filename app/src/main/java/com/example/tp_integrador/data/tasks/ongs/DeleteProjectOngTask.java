package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteProjectOngTask extends AsyncTask<Object, Void, Boolean> {


    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";


    @Override
    protected Boolean doInBackground(Object... params) {
        int id = (int) params[0];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertQuery = "DELETE FROM Proyectos_ong WHERE id_proyecto = "+ id;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();

                return rowsAffected > 0; // Devuelve true si se insertó correctamente.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si hubo un error en la inserción.
        }
    }
}
