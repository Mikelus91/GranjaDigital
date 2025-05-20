package main.java.com.granja.gestores;

import main.java.com.granja.util.Logger;
import main.java.com.granja.util.EntradaSegura;
import main.java.com.granja.model.Animal;
import main.java.com.granja.service.AnimalService;
import main.java.com.granja.model.Empleado;
import main.java.com.granja.service.EmpleadoService;
import main.java.com.granja.service.ActividadService;
import main.java.com.granja.model.Actividad;
import main.java.com.granja.model.Actividad.TipoActividad;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorActividad {
    static Scanner scanner = new Scanner(System.in);
    private static final ActividadService actividadService = new ActividadService();
    private static final AnimalService animalService = new AnimalService();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static void registrarActividad() {
        try {
            System.out.println("\n=== REGISTRAR NUEVA ACTIVIDAD ===");
            
            System.out.println("Tipo de actividad:");
            System.out.println("1-ORDEÑE");
            System.out.println("2-ALIMENTACIÓN");
            System.out.println("3-VACUNACIÓN");
            System.out.println("4-LIMPIEZA");
            TipoActividad tipoActividad = TipoActividad.values()[Integer.parseInt(scanner.nextLine()) - 1];
            
            LocalDate fecha = EntradaSegura.leerFechaSegura("Fecha (dd/MM/yyyy): ");
            
            //System.out.print("Hora (HH:mm): ");
            LocalTime hora = EntradaSegura.leerHora("Hora (HH:mm): ");
            
            GestorEmpleado.listarEmpleados();
            System.out.print("ID del empleado responsable: ");
            int idEmpleado = Integer.parseInt(scanner.nextLine());
            Empleado empleado = EmpleadoService.buscarEmpleadoPorId(idEmpleado);
            if (empleado == null) {
                throw new RuntimeException("Empleado no encontrado");
            }
            
            Actividad actividad = new Actividad(tipoActividad, fecha, hora, empleado);
            
            System.out.print("¿Involucra animales? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                GestorAnimal.listarAnimales();
                System.out.print("IDs de los animales (separados por comas): ");
                String[] ids = scanner.nextLine().split(",");
                List<Animal> animales = new ArrayList<>();
                for (String id : ids) {
                    Animal animal = animalService.buscarAnimalPorId(Integer.parseInt(id.trim()));
                    if (animal != null) {
                        animales.add(animal);
                    }
                }
                actividad.setAnimalesInvolucrados(animales);
            }
            
            actividadService.registrarActividad(actividad);
            System.out.println("Actividad registrada con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al registrar actividad: " + e.getMessage());
            Logger.error("Error en registro de actividad", e);
        }
    }

    public static void editarActividad() {
        try {
            listarActividades();
            System.out.print("\nIngrese el ID de la actividad a editar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Actividad actividad = actividadService.buscarPorId(id);
            if (actividad == null) {
                System.out.println("Actividad no encontrada");
                return;
            }

            System.out.println("Ingrese los nuevos datos (Enter para mantener el valor actual):");
            
            System.out.print("Fecha [" + actividad.getFecha() + "] (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine();
            if (!fechaStr.isEmpty()) {
                actividad.setFecha(LocalDate.parse(fechaStr, DATE_FORMATTER));
            }
            
            System.out.print("Hora [" + actividad.getHora() + "] (HH:mm): ");
            String horaStr = scanner.nextLine();
            if (!horaStr.isEmpty()) {
                actividad.setHora(LocalTime.parse(horaStr));
            }
            
            GestorEmpleado.listarEmpleados();
            System.out.print("ID del empleado responsable [" + actividad.getEmpleadoResponsable().getId() + "]: ");
            String idEmpleadoStr = scanner.nextLine();
            if (!idEmpleadoStr.isEmpty()) {
                Empleado empleado = EmpleadoService.buscarEmpleadoPorId(Integer.parseInt(idEmpleadoStr));
                if (empleado != null) {
                    actividad.setEmpleadoResponsable(empleado);
                }
            }
            
            List<Animal> animales = new ArrayList<>();
            System.out.print("¿Actualizar animales involucrados? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                GestorAnimal.listarAnimales();
                System.out.print("IDs de los animales (separados por comas): ");
                String[] ids = scanner.nextLine().split(",");
                for (String idAnimal : ids) {
                    Animal animal = animalService.buscarAnimalPorId(Integer.parseInt(idAnimal.trim()));
                    if (animal != null) {
                        animales.add(animal);
                    }
                }
                actividad.setAnimalesInvolucrados(animales);
            }
            
            actividadService.actualizarActividad(actividad);
            System.out.println("Actividad actualizada con éxito!");
        } catch (Exception e) {
            System.out.println("Error al editar actividad: " + e.getMessage());
            Logger.error("Error en edición de actividad", e);
        }
    }

    public static void eliminarActividad() {
    try {
        listarActividades();
        System.out.print("\nIngrese el ID de la actividad a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Está seguro de eliminar la actividad? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            boolean eliminada = actividadService.eliminarActividad(id);
            if (eliminada) {
                System.out.println("Actividad eliminada con éxito!");
            } else {
                System.out.println("No se encontró una actividad con ese ID.");
            }
        }
    } catch (Exception e) {
        System.out.println("Error al eliminar actividad: " + e.getMessage());
        Logger.error("Error en eliminación de actividad", e);
    }
}


    public static void listarActividades() {
        try {
            List<Actividad> actividades = actividadService.listarActividades();
            if (actividades.isEmpty()) {
                System.out.println("No hay actividades registradas");
                return;
            }
            
            System.out.println("\n=== LISTA DE ACTIVIDADES ===");
            System.out.printf("%-5s %-15s %-12s %-8s %-20s %-30s%n", 
                "ID", "TIPO", "FECHA", "HORA", "RESPONSABLE", "ANIMALES");
            
            for (Actividad actividad : actividades) {
                String animalesStr = actividad.getAnimalesInvolucrados() == null ? "N/A" : 
                    actividad.getAnimalesInvolucrados().stream()
                        .map(a -> a.getIdentificador())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                
                System.out.printf("%-5d %-15s %-12s %-8s %-20s %-30s%n",
                    actividad.getId(),
                    actividad.getTipoActividad(),
                    actividad.getFecha().format(DATE_FORMATTER),
                    actividad.getHora(),
                    actividad.getEmpleadoResponsable().getNombre(),
                    animalesStr);
            }
        } catch (Exception e) {
            System.out.println("Error al listar actividades: " + e.getMessage());
            Logger.error("Error en listado de actividades", e);
        }
    }
}