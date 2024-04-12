package com.softtek.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails {
    private int idOrden;
    private int idProducto;
    private double precioUnitario;
    private int cantidad;
    private double descuento;

    @Override
    public String toString() {
        return "\nOrderDetails{" +
                "idOrden=" + idOrden +
                ", idProducto='" + idProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidad=" + cantidad +
                ", descuento=" + descuento +
                '}';
    }
}
