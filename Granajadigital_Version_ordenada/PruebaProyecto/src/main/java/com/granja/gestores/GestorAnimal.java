package main.java.com.granja.gestores;

import main.java.com.granja.util.Logger;
import main.java.com.granja.util.EntradaSegura;
import main.java.com.granja.model.Animal;
import main.java.com.granja.service.AnimalService;
import main.java.com.granja.model.Animal.EstadoSalud;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GestorAnimal {
    static Scanner scanner = new Scanner(System.in);
    private static final AnimalService animalService = new AnimalService();
    public static void registrarAnimal() {
        try {
            System.out.println("\n=== REGISTRAR NUEVO ANIMAL ===");
            
            String identificador;
            while (true) {
            identificador = EntradaSegura.leerCadena("Identificador (ej. BOV-001): ");
            if (identificador.matches("[A-Z]{3}-\\d{3}")) break;
            System.out.println("Formato inválido. Debe ser XXX-000 (3 letras mayúsculas, guión, 3 números)");
            }
            
            String especie = EntradaSegura.leerNombreSeguro("Especie: ");
            String raza = EntradaSegura.leerNombreSeguro("Raza: ");
            LocalDate fechaNacimiento = EntradaSegura.leerFechaSegura("Fecha de nacimiento (dd/MM/yyyy): ");
            
            System.out.println("Estado de salud:");
            for (int i = 0; i < EstadoSalud.values().length; i++) {
                System.out.println((i + 1) + ". " + EstadoSalud.values()[i]);
            }
            int estadoSaludIndex = EntradaSegura.leerEnteroSeguro("Selecciona una opción: ", 1, 5) - 1;
            EstadoSalud estadoSalud = EstadoSalud.values()[estadoSaludIndex];
            
            String ubicacion = EntradaSegura.leerCadena("Ubicación: ");
            
            Animal animal = new Animal(identificador, especie, raza, fechaNacimiento, estadoSalud, ubicacion);
            animalService.registrarAnimal(animal);
            System.out.println("Animal registrado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al registrar animal: " + e.getMessage());
            Logger.error("Error en registro de animal", e);
        }
    }

    public static void listarAnimales() {
        try {
            List<Animal> animales = animalService.listarAnimales();
            if (animales.isEmpty()) {
                System.out.println("No hay animales registrados");
                return;
            }
            
            System.out.println("\n=== LISTA DE ANIMALES ===");
            System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s%n", 
                "ID", "IDENTIFICADOR", "ESPECIE", "RAZA", "ESTADO SALUD", "UBICACIÓN");
            
            for (Animal animal : animales) {
                System.out.printf("%-10d %-15s %-15s %-15s %-15s %-15s%n",
                    animal.getId(),
                    animal.getIdentificador(),
                    animal.getEspecie(),
                    animal.getRaza(),
                    animal.getEstadoSalud(),
                    animal.getUbicacion());
            }
        } catch (Exception e) {
            System.out.println("Error al listar animales: " + e.getMessage());
            Logger.error("Error en listado de animales", e);
        }
    }

    public static void editarAnimal() {
        try {
            listarAnimales();
            int id = EntradaSegura.leerEnteroOpcional(scanner, "Ingrese el ID del animal a editar: ");
            Animal animal = animalService.buscarAnimalPorId(id);
            if (animal == null) {
                System.out.println("Animal no encontrado");
                return;
            }
            
            System.out.println("\nEditando animal ID: " + animal.getId());
            System.out.println("Deja en blanco para mantener el valor actual.");
            
            String nuevaEspecie = EntradaSegura.leerCadenaOpcional("Especie", animal.getEspecie());
            String nuevaRaza = EntradaSegura.leerCadenaOpcional("Raza", animal.getRaza());
            String nuevaUbicacion = EntradaSegura.leerCadenaOpcional("Ubicación", animal.getUbicacion());
            
            animal.setEspecie(nuevaEspecie);
            animal.setRaza(nuevaRaza);
            animal.setUbicacion(nuevaUbicacion);
            
            animalService.actualizarAnimal(animal);
            System.out.println("Animal actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al editar animal: " + e.getMessage());
            Logger.error("Error en edición de animal", e);
        }
    }
    
    public static void eliminarAnimal() {
        try {
            listarAnimales();
            int id = EntradaSegura.leerEnteroOpcional(scanner, "Ingrese el ID del animal a eliminar: ");
            boolean confirmacion = EntradaSegura.leerBooleano("¿Está seguro que desea eliminar este animal? (s/n): ");
            if (!confirmacion) {
                System.out.println("Eliminación cancelada.");
                return;
            }
            
            boolean eliminado = animalService.eliminarAnimal(id);
            if (eliminado) {
                System.out.println("Animal eliminado con éxito.");
            } else {
                System.out.println("No se encontró un animal con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar animal: " + e.getMessage());
            Logger.error("Error en eliminación de animal", e);
        }
    }
}