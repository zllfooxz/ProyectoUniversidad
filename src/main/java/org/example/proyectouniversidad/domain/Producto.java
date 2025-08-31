package org.example.proyectouniversidad.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
}
