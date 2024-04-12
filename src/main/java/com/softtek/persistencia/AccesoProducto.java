package com.softtek.persistencia;

import com.softtek.modelo.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesoProducto extends Conexion{
    public Statement sentencia;
    public ResultSet resultado;

    public List<Producto> allProducts() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT * FROM products;";
        List<Producto> productos = new ArrayList<>();
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        while(resultado.next()){
            productos.add(new Producto(resultado.getInt("product_id"),
                    resultado.getString("product_name"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("units_in_stock"),
                    resultado.getInt("category_id")));
        }
        return productos;
    }

    public void insertProduct(int product_id,
                              String product_name,
                              int supplier_id,
                              int category_id,
                              String quantity_per_unit,
                              int unit_price,
                              int units_in_stock,
                              int units_on_order,
                              int reorder_level,
                              int discount) throws SQLException, ClassNotFoundException {
        abrirConexion();

        String query = "INSERT INTO products VALUES (?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1,product_id);
        ps.setString(2,product_name);
        ps.setInt(3,supplier_id);
        ps.setInt(4,category_id);
        ps.setString(5,quantity_per_unit);
        ps.setInt(6,unit_price);
        ps.setInt(7,units_in_stock);
        ps.setInt(8,units_on_order);
        ps.setInt(9,reorder_level);
        ps.setInt(10,discount);
        ps.executeUpdate();
    }

    public Producto selectByID(int product_id) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "SELECT * FROM products WHERE product_id=?;";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1,product_id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            int id = rs.getInt("product_id");
            String nombre = rs.getString("product_name");
            double precio = rs.getDouble("unit_price");
            int stock = rs.getInt("units_in_stock");
            int categoria = rs.getInt("category_id");
            return new Producto(id, nombre, precio, stock, categoria);
        }
        return null;
    }

    public void updateStockByID(int id, int stock) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "UPDATE products SET units_in_stock=? WHERE product_id=?";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1,stock);
        ps.setInt(2,id);
        ps.executeUpdate();
    }

    public void deleteProductByID(int id) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "DELETE FROM products WHERE product_id=?;";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1,id);
        ps.executeUpdate();
    }
}
