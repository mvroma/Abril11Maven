package com.softtek.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
        //Atributos
        protected Connection miConexion;

        //Métodos
        public void abrirConexion() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            miConexion = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/northwind",
                    "postgres",
                    "123456789");
            System.out.println("Exito al abrir la conexion");
        }
}
