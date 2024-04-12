package com.softtek.persistencia;

import com.softtek.modelo.Cliente;
import com.softtek.modelo.OrderDetails;
import com.softtek.modelo.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccesoOrder extends Conexion {
    public Statement sentencia;
    public ResultSet resultado;

    public List<OrderDetails> allOrders() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT * FROM order_details;";
        List<OrderDetails> ordenes = new ArrayList<>();
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        while (resultado.next()) {
            ordenes.add(new OrderDetails(resultado.getInt("order_id"),
                    resultado.getInt("product_id"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("quantity"),
                    resultado.getDouble("discount")));
        }
        return ordenes;
    }

    public void insertOrder(int order_id,
                            int product_id,
                            double unit_price,
                            int quantity,
                            double discount) throws SQLException, ClassNotFoundException {
        abrirConexion();

        String query = "INSERT INTO order_details VALUES (?,?,?,?,?);";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1, order_id);
        ps.setInt(2, product_id);
        ps.setDouble(3, unit_price);
        ps.setInt(4, quantity);
        ps.setDouble(5, discount);
        ps.executeUpdate();
    }

    public OrderDetails selectByID(int order_id) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "SELECT * FROM order_details WHERE order_id=?;";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1, order_id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int idOrden = rs.getInt("order_id");
            int idProducto = rs.getInt("product_id");
            Double unit_price = rs.getDouble("unit_price");
            int cantidad = rs.getInt("quantity");
            Double descuento = rs.getDouble("disscount");
            return new OrderDetails(idOrden, idProducto, unit_price, cantidad, descuento);
        }
        return null;
    }

    public void updateOrderByID(int idOrden, int idProducto) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "UPDATE order_details SET idProducto=? WHERE order_id=?";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1, idProducto);
        ps.setInt(2, idOrden);
        ps.executeUpdate();
    }

    public void deleteOrderByID(int idOrden) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String query = "DELETE FROM order_details WHERE order_id=?;";
        PreparedStatement ps = miConexion.prepareStatement(query);
        ps.setInt(1, idOrden);
        ps.executeUpdate();
    }

    public List<OrderDetails> getOrdenMayor30() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT * FROM order_details WHERE unit_price > 30";
        List<OrderDetails> orders = new ArrayList<>();
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        while (resultado.next()) {
            orders.add(new OrderDetails(
                    resultado.getInt("order_id"),
                    resultado.getInt("product_id"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("quantity"),
                    resultado.getDouble("discount")
            ));
        }
        return orders;
    }

    public List<OrderDetails> getOrdenDesc() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT * FROM order_details ORDER BY unit_price DESC";
        List<OrderDetails> orders = new ArrayList<>();
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        while (resultado.next()) {
            orders.add(new OrderDetails(
                    resultado.getInt("order_id"),
                    resultado.getInt("product_id"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("quantity"),
                    resultado.getDouble("discount")
            ));
        }
        return orders;
    }

    public OrderDetails getOrdenMin() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT * FROM order_details WHERE unit_price = (SELECT MIN(unit_price) FROM order_details)";
        OrderDetails ordenMin = null;
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        if (resultado.next()) {
            ordenMin = new OrderDetails(
                    resultado.getInt("order_id"),
                    resultado.getInt("product_id"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("quantity"),
                    resultado.getDouble("discount")
            );
        }
        return ordenMin;
    }

    public DoubleSummaryStatistics getEstadisticas() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT quantity FROM order_details";
        DoubleSummaryStatistics estadisticas = new DoubleSummaryStatistics();
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        while (resultado.next()) {
            estadisticas.accept(resultado.getInt("quantity"));
        }
        return estadisticas;
    }
    public Map<Integer, Integer> getProductoYSuma() throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT product_id, SUM(quantity) AS total_quantity FROM order_details GROUP BY product_id";
        Map<Integer, Integer> productQuantitiesSum = new HashMap<>();
        PreparedStatement ps = miConexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt("product_id");
            int totalQuantity = rs.getInt("total_quantity");
            productQuantitiesSum.put(productId, totalQuantity);
        }
        return productQuantitiesSum;
    }
}

