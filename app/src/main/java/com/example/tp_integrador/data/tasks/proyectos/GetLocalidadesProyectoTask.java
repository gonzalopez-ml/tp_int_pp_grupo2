package com.example.tp_integrador.data.tasks.proyectos;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Localidad;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Zona;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetLocalidadesProyectoTask extends AsyncTask<Object, Void, List<Localidad>> {

    private static final String DB_URL = "jdbc:mysql://btw.com.ar:3306/btw_db";
    private static final String USER = "btw_db";
    private static final String PASSWORD = "12345";

    @Override
    protected List<Localidad> doInBackground(Object... params) {
        List<Localidad> localidades = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM Localidades ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idLocalidad = resultSet.getInt("id_localidad");
                    String nombre = resultSet.getString("nombre");

                    // Llenamos la propiedad ong de la clase Project
                    Zona zona = new Zona();
                    zona.setId(resultSet.getInt("id_zona"));


                    Localidad localidad = new Localidad(idLocalidad, zona, nombre);
                    localidades.add(localidad);
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return localidades;
    }

    @Override
    protected void onPostExecute(List<Localidad> localidades) {
    }
}