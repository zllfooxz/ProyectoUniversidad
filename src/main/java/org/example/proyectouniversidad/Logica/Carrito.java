package org.example.proyectouniversidad.Logica;

import org.example.proyectouniversidad.domain.Producto;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Producto> productos;

    public Carrito(){
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }
    public double calcularTotal(){
        double total=0;
        for(Producto producto: productos){
            total+=producto.getSubtotal();
        }
        return total;
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }

}
