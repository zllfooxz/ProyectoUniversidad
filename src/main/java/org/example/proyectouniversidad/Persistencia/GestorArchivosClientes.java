package org.example.proyectouniversidad.Persistencia; // Mover a Persistencia

import org.example.proyectouniversidad.domain.Cliente;
import java.io.*;
import java.util.ArrayList;

public class GestorArchivosClientes {
    private static final String ARCHIVO_CLIENTES = "clientes.dat";
    private ArrayList<Cliente> clientes;

    public GestorArchivosClientes() {
        clientes = new ArrayList<>();
        cargarClientesDesdeArchivo();
    }

    private void cargarClientesDesdeArchivo() {
        File archivo = new File(ARCHIVO_CLIENTES);
        if (!archivo.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CLIENTES))) {
            clientes = (ArrayList<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
            clientes = new ArrayList<>();
        }
    }

    private void guardarClientesEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CLIENTES))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        guardarClientesEnArchivo();
    }

    public Cliente buscarClientePorId(String id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Cliente> getTodosClientes() {
        return new ArrayList<>(clientes);
    }
}