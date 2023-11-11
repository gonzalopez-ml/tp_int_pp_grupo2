package com.example.tp_integrador.data.tasks.voluntarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.domain.Voluntario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateVoluntarioTask extends AsyncTask<Voluntario, Void, Boolean> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Voluntario... params) {
        Voluntario voluntario = params[0];

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE Perfil_voluntarios " +
                    "SET nombre = ?, apellido = ?, dni = ?, habilidades = ?, telefono = ?, disponibilidad = ?, curriculum = ? " +
                    "WHERE id_perfil_voluntario = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, voluntario.getName());
                preparedStatement.setString(2, voluntario.getLastName());
                preparedStatement.setString(3, voluntario.getDni());
                preparedStatement.setString(4, voluntario.getSkills());
                preparedStatement.setString(5, voluntario.getPhone());
                preparedStatement.setString(6, voluntario.getAvailability());
                preparedStatement.setString(7, voluntario.getCv());
                //preparedStatement.setString(8, voluntario.getPhoto());
                preparedStatement.setInt(8, voluntario.getIdVoluntario());

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
