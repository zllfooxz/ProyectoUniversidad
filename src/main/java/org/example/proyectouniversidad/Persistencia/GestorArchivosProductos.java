package org.example.proyectouniversidad.Persistencia;



import org.example.proyectouniversidad.domain.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivosProductos {
    private static final String ARCHIVO_PRODUCTOS = "productos.dat";
    private ArrayList<Producto> inventario;

    public GestorArchivosProductos() {
        inventario = new ArrayList<>();
        cargarProductosDesdeArchivo();
        System.out.println("GestorArchivosProductos creado. Productos cargados: " + inventario.size());
    }


    private void cargarProductosDesdeArchivo() {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (!archivo.exists()) {
            System.out.println("Archivo no existe, se creará uno nuevo");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PRODUCTOS))) {
            inventario = (ArrayList<Producto>) ois.readObject();
            System.out.println("Productos cargados desde archivo: " + inventario.size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
            inventario = new ArrayList<>();
        }
    }

    //Guardar
    private void guardarProductosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRODUCTOS))) {
            oos.writeObject(inventario);
            System.out.println("Productos guardados en archivo: " + inventario.size());
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }


    public void agregarProducto(Producto producto) {
        inventario.add(producto);
        guardarProductosEnArchivo();
        System.out.println("Producto agregado: " + producto.getId());
    }

    public boolean eliminarProducto(String id) {
        boolean eliminado = inventario.removeIf(p -> p.getId().equals(id));
        if (eliminado) {
            guardarProductosEnArchivo();
        }
        return eliminado;
    }

    public boolean actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getId().equals(productoActualizado.getId())) {
                inventario.set(i, productoActualizado);
                guardarProductosEnArchivo();
                return true;
            }
        }
        return false;
    }

    public Producto buscarProducto(String id) {
        return inventario.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Producto> getTodosProductos() {
        return new ArrayList<>(inventario);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultados = new ArrayList<>();
        for (Producto p : inventario) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    public boolean restarStock(String idProducto, int cantidad) {
        Producto producto = buscarProducto(idProducto);
        if (producto != null && producto.getCantidad() >= cantidad) {
            producto.setCantidad(producto.getCantidad() - cantidad);
            guardarProductosEnArchivo();
            return true;
        }
        return false;
    }

    public int getTotalProductos() {
        return inventario.size();
    }
}
