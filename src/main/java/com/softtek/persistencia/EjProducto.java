package com.softtek.persistencia;

import com.softtek.modelo.Producto;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EjProducto {
    public static void obtenerCategoria2(AccesoProducto ap1) throws SQLException, ClassNotFoundException {
        Predicate<Producto> obtenerCategoria = x -> x.getCategoria() == 2;
        ap1.allProducts().stream()
                .filter(obtenerCategoria)
                .forEach(System.out::println);
    }

    public static void obtenerComienzoN(AccesoProducto ap1) throws SQLException, ClassNotFoundException {
        Predicate<Producto> obtenerComienzo = x -> x.getNombreProducto().startsWith("N");
        ap1.allProducts().stream()
                .filter(obtenerComienzo)
                .forEach(System.out::println);
    }

    public static void obtenerNombre(AccesoProducto ap1) throws SQLException, ClassNotFoundException {
        List<Producto> productosOrdenados = ap1.allProducts().stream()
                .sorted(Comparator.comparing(Producto::getNombreProducto))
                .collect(Collectors.toList());
        productosOrdenados.forEach(System.out::println);

    }
    public static void obtenerPrecioMax(AccesoProducto ap1) throws SQLException, ClassNotFoundException {
        List<Producto> productos = ap1.allProducts();
        double max = productos.stream()
                .mapToDouble(Producto::getPrecioUnitario)
                .max()
                .orElse(0);
        double min = productos.stream()
                .mapToDouble(Producto::getPrecioUnitario)
                .min()
                .orElse(0);
        System.out.println("Precio máximo: " + max);
        System.out.println("Precio mínimo: " + min);
    }
    public static void promedioPreciosProductos(AccesoProducto ap1) throws SQLException,ClassNotFoundException {

        List<Producto> listaProductos = ap1.allProducts();

        double promedioPrecios = listaProductos.stream().mapToDouble(Producto::getPrecioUnitario).average().orElse(0);


        System.out.println(promedioPrecios);
    }
    public static void obtenerCategoriaYCantidad(AccesoProducto ap1) throws SQLException,ClassNotFoundException {

        List<Producto> listaProductos = ap1.allProducts();

        Map<Integer, Long> cantidadPorCategoria = listaProductos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.counting()));
        cantidadPorCategoria.forEach((categoria, cantidad) ->
                System.out.println("Categoría " + categoria + ": " + cantidad + " productos"));

    }
}
