package main.java.com.granja.model;

import java.time.LocalDate;

public class Empleado {
    private int id;
    private String nombre;
    private Rol rol;
    private String telefono;
    private LocalDate fechaContratacion;
    private boolean activo;

    public enum Rol {
        VETERINARIO, PEON, ENCARGADO
    }

    public Empleado(String nombre, Rol rol, String telefono, LocalDate fechaContratacion) {
        this.nombre = nombre;
        this.rol = rol;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.activo = true;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}
