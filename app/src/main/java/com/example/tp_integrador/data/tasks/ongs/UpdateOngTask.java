package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
//import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;

import android.os.AsyncTask;

import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
//import com.example.tp_integrador.data.domain.Voluntario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOngTask extends AsyncTask<Ong, Void, Boolean> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Ong... params) {
       Ong ong = params[0];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE Perfil_ongs " +
                    "SET nombre = ?, descripcion = ?, logo = ?, telefono = ?, mail = ?, ubicacion = ? " +
                    "WHERE id_perfil_ong = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, ong.getName());
                preparedStatement.setString(2, ong.getDescription());
                preparedStatement.setString(3, ong.getLogo());
                preparedStatement.setString(4, ong.getPhone());
                preparedStatement.setString(5, ong.getMail());
                preparedStatement.setString(6, ong.getLocation());
                //preparedStatement.setString(7, voluntario.getCv());
                //preparedStatement.setString(8, voluntario.getPhoto());
                preparedStatement.setInt(7, ong.getIdOng());

                int rowsAffected = preparedStatement.executeUpdate();

                Boolean isUpdate= rowsAffected > 0;

                preparedStatement.close();
                return isUpdate;
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
