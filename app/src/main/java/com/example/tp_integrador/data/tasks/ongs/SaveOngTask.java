package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tp_integrador.data.domain.Ong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveOngTask extends AsyncTask<Object, Void, Boolean> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";


    @Override
    protected Boolean doInBackground(Object... params) {
        int idUser = (int) params[0];
        Ong ong = (Ong) params[1];
        Log.d("Aviso","cantidad pada asdasd");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO Perfil_ongs (id_usuario, nombre, descripcion, logo, telefono, mail, ubicacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, idUser);
                preparedStatement.setString(2, ong.getName());
                preparedStatement.setString(3, ong.getDescription());
                preparedStatement.setString(4, "logo");
                preparedStatement.setString(5, ong.getPhone());
                preparedStatement.setString(6, ong.getMail());
                preparedStatement.setString(7, ong.getLocation());

                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();

                Log.d("Aviso","cantidad pada: "+rowsAffected);

                return rowsAffected > 0; //
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