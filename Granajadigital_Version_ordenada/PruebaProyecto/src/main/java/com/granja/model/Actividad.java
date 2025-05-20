package main.java.com.granja.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Actividad {
    private int id;
    private TipoActividad tipoActividad;
    private LocalDate fecha;
    private LocalTime hora;
    private Empleado empleadoResponsable;
    private List<Animal> animalesInvolucrados;

    public enum TipoActividad {
        ORDEÑE("Ordeñe"),
        ALIMENTACION("Alimentación"),
        VACUNACION("Vacunación"),
        LIMPIEZA("Limpieza");

        private final String nombre;

        TipoActividad(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    public Actividad(TipoActividad tipoActividad, LocalDate fecha, LocalTime hora, Empleado empleadoResponsable) {
        this.tipoActividad = tipoActividad;
        this.fecha = fecha;
        this.hora = hora;
        this.empleadoResponsable = empleadoResponsable;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public TipoActividad getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public Empleado getEmpleadoResponsable() { return empleadoResponsable; }
    public void setEmpleadoResponsable(Empleado empleadoResponsable) { this.empleadoResponsable = empleadoResponsable; }
    public List<Animal> getAnimalesInvolucrados() { return animalesInvolucrados; }
    public void setAnimalesInvolucrados(List<Animal> animalesInvolucrados) { this.animalesInvolucrados = animalesInvolucrados; }
}