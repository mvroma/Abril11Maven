package com.softtek.persistencia;

import com.softtek.modelo.Cliente;
import com.softtek.modelo.Empleado;
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesoEmpleado extends Conexion{

    public Statement sentencia;
    public ResultSet rs;

    public List<Empleado> todosEmpleados() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT * FROM employees;";
        List<Empleado> empleados = new ArrayList<>();
        sentencia = miConexion.createStatement();
        rs = sentencia.executeQuery(sql);
        while(rs.next()){
            empleados.add(new Empleado(rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("title"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getDate("birth_date")));
        }
        return empleados;
    }

    public void insertEmpleado(int employee_id,
                               String last_name,
                               String first_name,
                               String title,
                               String title_of_courtesy,
                               String birth_date,
                               String hire_date,
                               String address,
                               String city,
                               String region,
                               String postal_code,
                               String country,
                               String home_phone,
                               String extension,
                               byte photo,
                               String notes,
                               int reports_to,
                               String photo_path) throws SQLException, ClassNotFoundException {
        abrirConexion();

        String query = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?, NULL,?, NULL,?);";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1,employee_id);
        ps.setString(2,last_name);
        ps.setString(3,first_name);
        ps.setString(4,title);
        ps.setString(5,title_of_courtesy);
        ps.setDate(6, Date.valueOf(birth_date));
        ps.setDate(7, Date.valueOf(hire_date));
        ps.setString(8,address);
        ps.setString(9,city);
        ps.setString(10,region);
        ps.setString(11,postal_code);
        ps.setString(12,country);
        ps.setString(13,home_phone);
        ps.setString(14,extension);
        //ps.setByte(15,photo);
        ps.setString(15,notes);
        //ps.setInt(16,reports_to);
        ps.setString(16,photo_path);
        ps.executeUpdate();
    }

    public Empleado selectByID(int employee_id) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "SELECT * FROM employees WHERE employee_id=?;";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1,employee_id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            int id = rs.getInt("employee_id");
            String nombre = rs.getString("first_name");
            String apellido = rs.getString("last_name");
            String puesto = rs.getString("title");
            String direccion = rs.getString("address");
            String ciudad = rs.getString("city");
            Date fechaNac = rs.getDate("birth_date");
            return new Empleado(id, nombre, apellido, puesto, direccion, ciudad, fechaNac);
        }
        return null;
    }

    public void updateAddressByID(int id, String address) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "UPDATE employees SET address=? WHERE employee_id=?;";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setString(1,address);
        ps.setInt(2,id);
        ps.executeUpdate();
    }

    public void deleteEmpleadoByID(int id) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "DELETE FROM employees WHERE employee_id=?;";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1,id);
        ps.executeUpdate();
    }
}