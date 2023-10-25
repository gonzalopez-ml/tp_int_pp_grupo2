package com.example.tp_integrador.data.tasks.usuarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUsuarioTask extends AsyncTask<Usuario, Void, Usuario> {
    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Usuario doInBackground(Usuario... usuarios) {
        Usuario usuario = usuarios[0];

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Class.forName("com.mysql.jdbc.Driver");

            String selectQuery = "SELECT * FROM Usuarios WHERE mail = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, usuario.getMail());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Usuario usuarioBase = new Usuario();
                    usuarioBase.setIdUser(Integer.valueOf(resultSet.getString("id_usuario")));
                    usuarioBase.setName(resultSet.getString("nombre"));
                    usuarioBase.setApellido(resultSet.getString("apellido"));
                    usuarioBase.setMail(resultSet.getString("mail"));

                    return usuarioBase;
                } else {
                    return null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
    }
}
