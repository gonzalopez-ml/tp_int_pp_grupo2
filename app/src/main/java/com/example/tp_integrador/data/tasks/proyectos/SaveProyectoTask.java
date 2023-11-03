package com.example.tp_integrador.data.tasks.proyectos;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveProyectoTask extends AsyncTask<Object,Void,Boolean> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected Boolean doInBackground(Object... params) {
        Proyecto proyecto = (Proyecto) params[0];
        Integer idOng = (Integer) params[1];
        Integer idProyecto = null;

// REVISAR Valor null en el id
        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD)){
            String insertQuery= "INSERT INTO proyectos_ong (id_proyecto,id_perfil_ong, nombre, necesidades,descripcion,ubicacion,disponibilidad) VALUES (?, ?, ?, ?, ?, ?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
                preparedStatement.setInt(1,idProyecto);
                preparedStatement.setObject(2,idOng);
                preparedStatement.setString(3,proyecto.getNombre());
                preparedStatement.setString(4,proyecto.getObjetivos());
                preparedStatement.setString(5,proyecto.getDescripcion());
                preparedStatement.setString(6,proyecto.getUbicacion());
                preparedStatement.setString(7,proyecto.getDisponibilidad());
                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();

                return rowsAffected > 0; // Devuelve true si se insertó correctamente.

            }

        }catch (SQLException e){
            e.printStackTrace();
            return false;//Devuelve false si hubo un error en la insercion.
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
    }
}
