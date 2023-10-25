package com.example.tp_integrador.data.tasks.usuarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveUsuarioTask extends AsyncTask<Usuario, Void, Boolean> {
    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Usuario... usuarios) {
        Usuario usuario = usuarios[0];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO Usuarios (nombre, apellido, mail, password, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, usuario.getName());
                preparedStatement.setString(2, usuario.getApellido());
                preparedStatement.setString(3, usuario.getMail());
                preparedStatement.setString(4, usuario.getPassword());
                preparedStatement.setString(5, usuario.getTipoUser().getId().toString());

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
