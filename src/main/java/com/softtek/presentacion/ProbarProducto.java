package com.softtek.presentacion;

import com.softtek.modelo.Producto;
import com.softtek.persistencia.AccesoProducto;
import com.softtek.persistencia.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.*;

public class ProbarProducto {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //selectAll();
        AccesoProducto producto = new AccesoProducto();

        //int id = 120;
        //createProduct(producto, id);
        //selectProduct(producto, id);
        //updateProduct(producto, id,23);
        //deleteProduct(producto, id);
    }

    public static void selectAll() throws SQLException, ClassNotFoundException {
        AccesoProducto productos = new AccesoProducto();
        System.out.println(productos.allProducts());
    }

    public static void createProduct(AccesoProducto producto, int id) throws SQLException, ClassNotFoundException {
        int product_id = id;
        String product_name = "Sillas";
        int supplier_id = 8;
        int category_id = 3;
        String quantity_per_unit = "1 unidad";
        int unit_price = 45;
        int units_in_stock = 78;
        int units_on_order = 34;
        int reorder_level = 3;
        int discount = 0;

        producto.insertProduct(product_id,
                product_name,
                supplier_id,
                category_id,
                quantity_per_unit,
                unit_price,
                units_in_stock,
                units_on_order,
                reorder_level,
                discount);

        System.out.println(producto.selectByID(product_id));
    }

    public static void selectProduct(AccesoProducto producto, int id) throws SQLException, ClassNotFoundException {
        System.out.println(producto.selectByID(id));
    }

    public static void updateProduct(AccesoProducto producto, int id, int stock) throws SQLException, ClassNotFoundException {
        producto.updateStockByID(id, stock);
        System.out.println(producto.selectByID(id));
    }

    public static void deleteProduct(AccesoProducto producto, int id) throws SQLException, ClassNotFoundException {
        producto.deleteProductByID(id);
        System.out.println("El producto con id :" + id + " ha sido borrado");
    }
}

