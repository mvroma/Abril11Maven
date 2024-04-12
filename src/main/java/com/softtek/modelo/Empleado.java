package com.softtek.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Empleado {

    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String puesto;
    private String direccion;
    private String ciudad;
    private Date fechaNac;

    @Override
    public String toString() {
        return "\nEmpleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", puesto='" + puesto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                '}';
    }
}
