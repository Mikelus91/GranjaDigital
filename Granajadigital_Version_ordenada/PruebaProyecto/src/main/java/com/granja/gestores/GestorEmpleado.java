package main.java.com.granja.gestores;

import main.java.com.granja.util.Logger;
import main.java.com.granja.util.EntradaSegura;
import main.java.com.granja.model.Empleado;
import main.java.com.granja.model.Empleado.Rol;
import main.java.com.granja.service.EmpleadoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class GestorEmpleado {
    static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void registrarEmpleado() {
        try {
            System.out.println("\n=== REGISTRAR NUEVO EMPLEADO ===");
            
            String nombre = EntradaSegura.leerNombreSeguro("Nombre: ");
            
            System.out.println("Rol (1-VETERINARIO, 2-PEON, 3-ENCARGADO): ");
            int rolSeleccionado = EntradaSegura.leerEnteroSeguro("Rol (1-VETERINARIO, 2-PEON, 3-ENCARGADO): ", 1, Rol.values().length);
            Rol rol = Rol.values()[rolSeleccionado - 1];

            
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            LocalDate fechaContratacion = EntradaSegura.leerFechaSegura("Fecha de contratación (dd/MM/yyyy): ");
            
            Empleado empleado = new Empleado(nombre, rol, telefono, fechaContratacion);
            EmpleadoService.registrarEmpleado(empleado);
            
            System.out.println("Empleado registrado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al registrar empleado: " + e.getMessage());
            Logger.error("Error en registro de empleado", e);
        }
    }

    public static void listarEmpleados() {
        try {
            List<Empleado> empleados = EmpleadoService.listarEmpleados();
            if (empleados.isEmpty()) {
                System.out.println("No hay empleados registrados");
                return;
            }
            
            System.out.println("\n=== LISTA DE EMPLEADOS ===");
            System.out.printf("%-5s %-20s %-15s %-15s %-15s%n", 
                "ID", "NOMBRE", "ROL", "TELÉFONO", "CONTRATACIÓN");
            
            for (Empleado empleado : empleados) {
                System.out.printf("%-5d %-20s %-15s %-15s %-15s%n",
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getRol(),
                    empleado.getTelefono(),
                    empleado.getFechaContratacion().format(DATE_FORMATTER));
            }
        } catch (Exception e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
            Logger.error("Error en listado de empleados", e);
        }
    }

    public static void editarEmpleado() {
        try {
            listarEmpleados();
            System.out.print("\nIngrese el ID del empleado a editar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Empleado empleado = EmpleadoService.buscarEmpleadoPorId(id);
            if (empleado == null) {
                System.out.println("Empleado no encontrado");
                return;
            }

            System.out.println("Ingrese los nuevos datos (Enter para mantener el valor actual):");
            
            System.out.print("Nombre [" + empleado.getNombre() + "]: ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                empleado.setNombre(nombre);
            }
            
            System.out.print("Teléfono [" + empleado.getTelefono() + "]: ");
            String telefono = scanner.nextLine();
            if (!telefono.isEmpty()) {
                empleado.setTelefono(telefono);
            }
            
            System.out.println("Rol actual [" + empleado.getRol() + "]");
            System.out.println("Nuevo rol (1-VETERINARIO, 2-PEON, 3-ENCARGADO, Enter para mantener): ");
            String rolStr = scanner.nextLine();
            if (!rolStr.isEmpty()) {
                empleado.setRol(Rol.values()[Integer.parseInt(rolStr) - 1]);
            }
            
            EmpleadoService.actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al editar empleado: " + e.getMessage());
            Logger.error("Error en edición de empleado", e);
        }
    }

    public static void eliminarEmpleado() {
        try {
            listarEmpleados();
            System.out.print("\nIngrese el ID del empleado a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("¿Está seguro de eliminar el empleado? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                EmpleadoService.eliminarEmpleado(id);
                System.out.println("Empleado eliminado con éxito!");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            Logger.error("Error en eliminación de empleado", e);
        }
    }
}