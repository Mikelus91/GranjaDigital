package com.granja;

import main.java.com.granja.util.EntradaSegura;
import main.java.com.granja.gestores.GestorActividad;
import main.java.com.granja.gestores.GestorAnimal;
import main.java.com.granja.gestores.GestorEmpleado;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de animales");
            System.out.println("2. Gestión de empleados");
            System.out.println("3. Gestión de actividades");
            System.out.println("4. Salir");

            int opcion = EntradaSegura.leerEnteroSeguro("Selecciona una opción: ", 1, 4);

            switch (opcion) {
                case 1:
                    menuGestionAnimales();
                    break;
                case 2:
                    menuGestionEmpleados();
                    break;
                case 3:
                    menuGestionActividades();
                    break;
                case 4:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default: System.out.println("Opción no válida");
            }
        }
    }

    private static void menuGestionAnimales() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Animales ---");
            System.out.println("1. Dar de alta animal");
            System.out.println("2. Mostrar animales");
            System.out.println("3. Modificar animal");
            System.out.println("4. Eliminar animal");
            System.out.println("5. Volver al menú principal");

            int opcion = EntradaSegura.leerEnteroSeguro("Selecciona una opción: ", 1, 5);

            switch (opcion) {
                case 1:
                    GestorAnimal.registrarAnimal();
                    break;
                case 2:
                    GestorAnimal.listarAnimales();
                    break;
                case 3:
                    GestorAnimal.editarAnimal();
                    break;
                case 4:
                    GestorAnimal.eliminarAnimal();
                    break;
                case 5:
                    volver = true;
                    break;
                default: System.out.println("Opción no válida");
            }
        }
    }

    private static void menuGestionEmpleados() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Empleados ---");
            System.out.println("1. Dar de alta empleado");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Modificar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Volver al menú principal");

            int opcion = EntradaSegura.leerEnteroSeguro("Selecciona una opción: ", 1, 5);

            switch (opcion) {
                case 1:
                    GestorEmpleado.registrarEmpleado();
                    break;
                case 2:
                    GestorEmpleado.listarEmpleados();
                    break;
                case 3:
                    GestorEmpleado.editarEmpleado();
                    break;
                case 4:
                    GestorEmpleado.eliminarEmpleado();
                    break;
                case 5:
                    volver = true;
                    break;
                default: System.out.println("Opción no válida");
            }
        }
    }

    private static void menuGestionActividades() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Actividades ---");
            System.out.println("1. Dar de alta actividad");
            System.out.println("2. Mostrar actividades");
            System.out.println("3. Modificar actividad");
            System.out.println("4. Eliminar actividad");
            System.out.println("5. Volver al menú principal");

            int opcion = EntradaSegura.leerEnteroSeguro("Selecciona una opción: ", 1, 5);

            switch (opcion) {
                case 1:
                    GestorActividad.registrarActividad();
                    break;
                case 2:
                    GestorActividad.listarActividades();
                    break;
                case 3:
                    GestorActividad.editarActividad();
                    break;
                case 4:
                    GestorActividad.eliminarActividad();
                    break;
                case 5:
                    volver = true;
                    break;
                default: System.out.println("Opción no válida");
            }
        }
    }
}