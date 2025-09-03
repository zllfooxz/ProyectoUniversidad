package org.example.proyectouniversidad.domain;

public class Cliente {
   private  String id;
   private  String nombre;
   private String direccion;
   private String correo;

    public Cliente() {
    }
    public Cliente(String id, String nombre, String direccion, String correo) {

        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
