package com.example.tp_integrador.data.tasks.voluntarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Voluntario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveVoluntarioTask extends AsyncTask<Object, Void, Boolean> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";


    @Override
    protected Boolean doInBackground(Object... params) {
        int idUser = (int) params[0];
        Voluntario voluntario = (Voluntario) params[1];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO Perfil_voluntarios (id_usuario, nombre, apellido, dni, habilidades, telefono, disponibilidad, curriculum, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, idUser);
                preparedStatement.setString(2, voluntario.getName());
                preparedStatement.setString(3, voluntario.getLastName());
                preparedStatement.setString(4, voluntario.getDni());
                preparedStatement.setString(5, voluntario.getSkills());
                preparedStatement.setString(6, voluntario.getPhone());
                preparedStatement.setString(7, voluntario.getAvailability());
                preparedStatement.setString(8, voluntario.getCv());
                preparedStatement.setString(9, voluntario.getPhoto());

                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();

                return rowsAffected > 0; // Devuelve true si se insertó correctamente.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si hubo un error en la inserción.
        }

    }

    @Override
    protected void onPostExecute(Boolean success) {
    }
}