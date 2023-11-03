package com.example.tp_integrador.data.tasks.usuarios;

import android.os.AsyncTask;
import com.example.tp_integrador.data.domain.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUsuarioTask extends AsyncTask<Usuario, Void, Boolean> {
    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Usuario... usuarios) {
        Usuario usuario = usuarios[0];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE Usuarios SET mail=?, password=?, tipo_usuario=? WHERE id_usuario=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, usuario.getMail());
                preparedStatement.setString(2, usuario.getPassword());
                preparedStatement.setString(3, usuario.getTipoUser().getId().toString());
                preparedStatement.setInt(4, usuario.getIdUser());

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
