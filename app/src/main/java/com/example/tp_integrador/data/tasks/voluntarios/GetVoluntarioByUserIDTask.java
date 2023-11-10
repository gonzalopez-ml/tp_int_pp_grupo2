package com.example.tp_integrador.data.tasks.voluntarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVoluntarioByUserIDTask extends AsyncTask<Integer, Void, Voluntario> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Voluntario doInBackground(Integer... params) {
        int idUsuario = params[0];

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Class.forName("com.mysql.jdbc.Driver");

            String selectQuery = "SELECT * FROM Perfil_voluntarios " +
                                    "INNER JOIN Usuarios ON Perfil_voluntarios.id_usuario = Usuarios.id_usuario " +
                                    "WHERE Perfil_voluntarios.id_usuario = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, idUsuario);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Voluntario voluntario = new Voluntario();
                    Usuario usuario = new Usuario();
                    TipoUser tipoUser = new TipoUser();

                    voluntario.setIdVoluntario(resultSet.getInt("id_perfil_voluntario"));
                    voluntario.setName(resultSet.getString("nombre"));
                    voluntario.setLastName(resultSet.getString("apellido"));
                    voluntario.setDni(resultSet.getString("dni"));
                    voluntario.setSkills(resultSet.getString("habilidades"));
                    voluntario.setPhone(resultSet.getString("telefono"));
                    voluntario.setAvailability(resultSet.getString("disponibilidad"));
                    voluntario.setCv(resultSet.getString("curriculum"));
                    voluntario.setPhoto(resultSet.getString("foto"));

                    usuario.setIdUser(idUsuario);
                    usuario.setMail(resultSet.getString("mail"));
                    usuario.setPassword(resultSet.getString("password"));

                    tipoUser.setId(Integer.parseInt(resultSet.getString("tipo_usuario")));
                    usuario.setTipoUser(tipoUser);
                    voluntario.setUsuario(usuario);

                    return voluntario;
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
    protected void onPostExecute(Voluntario voluntario) {
    }
}

