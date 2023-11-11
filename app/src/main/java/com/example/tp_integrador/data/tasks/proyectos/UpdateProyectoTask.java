package com.example.tp_integrador.data.tasks.proyectos;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProyectoTask extends AsyncTask<Object, Void, Boolean> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Object... params) {
        Proyecto proyecto = (Proyecto) params[0];

        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD)){
            String updateQuery = "UPDATE Proyectos_ong SET id_perfil_ong = ?, nombre = ?, necesidades = ?, descripcion = ?, disponibilidad = ?, ubicacion = ? WHERE id_proyecto = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                preparedStatement.setObject(1, proyecto.getOng().getIdOng());
                preparedStatement.setString(2, proyecto.getNombre());
                preparedStatement.setString(3, proyecto.getObjetivos());
                preparedStatement.setString(4, proyecto.getDescripcion());
                preparedStatement.setString(5, proyecto.getDisponibilidad());
                preparedStatement.setString(6, proyecto.getUbicacion());
                preparedStatement.setObject(7, proyecto.getIdProyecto());

                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                return rowsAffected > 0;
            }

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
