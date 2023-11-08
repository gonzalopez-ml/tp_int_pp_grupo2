package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import android.util.Log;

public class GetOngTask extends AsyncTask<Integer, Void, Ong> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Ong doInBackground(Integer... params) {
        int idOng = params[0];

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Class.forName("com.mysql.jdbc.Driver");

            String selectQuery = "SELECT * FROM Perfil_ongs " +
                    "INNER JOIN Usuarios ON Perfil_ongs.id_usuario = Usuarios.id_usuario " +
                    "WHERE Perfil_ongs.id_perfil_ong = ?";

            Log.d("aviso","Query: "+selectQuery);

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, idOng);
                ResultSet resultSet = preparedStatement.executeQuery();
                Log.d("aviso","Pasa Query 2: "+selectQuery);
                if (resultSet.next()) {
                    Log.d("aviso","pasa por next");

                    Ong ong = new Ong();
                    Usuario usuario = new Usuario();
                    TipoUser tipoUser = new TipoUser();

                    Log.d("Aviso","Pasa Nombre: "+resultSet.getString("nombre"));
                    Log.d("Aviso","Pasa id: "+idOng);

                    ong.setIdOng(idOng);
                    ong.setName(resultSet.getString("nombre"));
                    ong.setDescription(resultSet.getString("descripcion"));
                    ong.setLogo(resultSet.getString("logo"));
                    ong.setPhone(resultSet.getString("telefono"));
                    ong.setLocation(resultSet.getString("ubicacion"));

                    usuario.setIdUser(resultSet.getInt("id_usuario"));
                    usuario.setMail(resultSet.getString("mail"));
                    usuario.setPassword(resultSet.getString("password"));

                    tipoUser.setId(Integer.parseInt(resultSet.getString("tipo_usuario")));
                    usuario.setTipoUser(tipoUser);
                    //ong.setIdOng(usuario);

                    Log.d("Aviso","Ruturn ong GetOngTask");
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


