package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetOngByUserIDTask extends AsyncTask<Integer, Void, Ong> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Ong doInBackground(Integer... params) {
        int idUsuario = params[0];

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Class.forName("com.mysql.jdbc.Driver");

            String selectQuery = "SELECT * FROM Perfil_ongs " +
                                    "INNER JOIN Usuarios ON Perfil_ongs.id_usuario = Usuarios.id_usuario " +
                                    "WHERE Perfil_ongs.id_usuario = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, idUsuario);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Ong ong = new Ong();
                    Usuario usuario = new Usuario();
                    TipoUser tipoUser = new TipoUser();

                    ong.setIdOng(resultSet.getInt("id_perfil_ong"));
                    ong.setName(resultSet.getString("nombre"));
                    ong.setDescription(resultSet.getString("descripcion"));
                    ong.setLogo(resultSet.getString("logo"));
                    ong.setPhone(resultSet.getString("telefono"));
                    ong.setMail(resultSet.getString("mail"));
                    ong.setLocation(resultSet.getString("ubicacion"));

                    usuario.setIdUser(idUsuario);
                    usuario.setMail(resultSet.getString("mail"));
                    usuario.setPassword(resultSet.getString("password"));

                    tipoUser.setId(Integer.parseInt(resultSet.getString("tipo_usuario")));
                    usuario.setTipoUser(tipoUser);
                    ong.setUsuario(usuario);

                    return ong;
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
    protected void onPostExecute(Ong ong) {
    }
}

