package com.softtek.presentacion;

import com.softtek.persistencia.AccesoProducto;
import com.softtek.persistencia.AccesoEmpleado;
import java.sql.SQLException;
import com.softtek.persistencia.EjProducto;

import static com.softtek.persistencia.EjProducto.obtenerCategoria2;

public class ProbarEjProduct {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            AccesoProducto ap1 = new AccesoProducto();
        try {
            obtenerCategoria2(ap1);
            //obtenerComienzoN(ap1);
            //obtenerNombre(ap1);
            //obtenerPrecioMax(ap1);
            //promedioPreciosProductos(ap1);
            //obtenerCategoriaYCantidad(ap1)
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
