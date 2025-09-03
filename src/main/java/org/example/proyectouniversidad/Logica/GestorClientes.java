package org.example.proyectouniversidad.Logica;

import org.example.proyectouniversidad.domain.Cliente;

import java.util.ArrayList;

public class GestorClientes {
    private ArrayList<Cliente> clientes;

    public GestorClientes() {}
    public GestorClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    public void guardarClientes(ArrayList<Cliente> cliente) {
        clientes.addAll(cliente);
    }
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Cliente BuscarClienteId(String id) {
        for(Cliente cliente : clientes) {
            if(cliente.getId().equals(id))
                return cliente;
        }
        return null;
    }
}
