package main.java.com.granja.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntradaSegura {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int leerEnteroSeguro(String mensaje, int min, int max) {
        int numero;
        while (true) {
            System.out.print(mensaje + " [" + min + " - " + max + "]: ");
            try {
                numero = Integer.parseInt(scanner.nextLine().trim());
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println("El número debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
            }
        }
    }

    public static LocalTime leerHora(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (HH:mm): ");
            String entrada = scanner.nextLine();
            try {
                return LocalTime.parse(entrada, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Usa el formato exacto: HH:mm (ej. 14:30)");
            }
        }
    }

    public static boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("s") || input.equals("si")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            System.out.println("Por favor, responde con 's' o 'n'.");
        }
    }

    public static LocalDate leerFechaSegura(String mensaje) {

    while (true) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine();
        try {
            LocalDate fecha = LocalDate.parse(entrada, DATE_FORMATTER);
            if (fecha.isAfter(LocalDate.now())) {
                System.out.println("No se pueden registrar fechas futuras.");
            } else {
                return fecha;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Usa el formato exacto: dd/MM/yyyy (ej. 01/06/2005)");
        }
    }
}

    public static String leerNombreSeguro(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String nombre = scanner.nextLine().trim();
            if (nombre.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]{3,50}")) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Usa solo letras y espacios. Mínimo 3 letras.");
            }
        }
    }

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public static String leerCadenaOpcional(String mensaje, String valorActual) {
        System.out.print(mensaje + " [" + valorActual + "]: ");
        String entrada = scanner.nextLine().trim();
        return entrada.isEmpty() ? valorActual : entrada;
    }

    public static int leerEnteroOpcional(String mensaje, int valorActual) {
        while (true) {
            System.out.print(mensaje + " [" + valorActual + "]: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                return valorActual;
            }
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
            }
        }
    }

    public static int leerEnteroOpcional(Scanner scanner2, String string) {
        
        throw new UnsupportedOperationException("Unimplemented method 'leerEnteroOpcional'");
    }
}