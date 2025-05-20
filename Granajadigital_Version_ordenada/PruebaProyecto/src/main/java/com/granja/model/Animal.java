package main.java.com.granja.model;

import java.time.LocalDate;

public class Animal {
    private int id;
    private String identificador;
    private String especie;
    private String raza;
    private LocalDate fechaNacimiento;
    private EstadoSalud estadoSalud;
    private String ubicacion;
    private EstadoAnimal estado;
    private LocalDate fechaEstado;
    private String notas;

    public enum EstadoSalud {
        EXCELENTE, BUENO, REGULAR, MALO, CRITICO
    }

    public enum EstadoAnimal {
        ACTIVO, VENDIDO, FALLECIDO, TRASLADADO
    }

    // Constructor existente modificado
    public Animal(String identificador, String especie, String raza, 
                 LocalDate fechaNacimiento, EstadoSalud estadoSalud, 
                 String ubicacion) {
        this.identificador = identificador;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoSalud = estadoSalud;
        this.ubicacion = ubicacion;
        this.estado = EstadoAnimal.ACTIVO;
        this.fechaEstado = LocalDate.now();
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EstadoSalud getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(EstadoSalud estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }

    public void setEstado(EstadoAnimal estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(LocalDate fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
