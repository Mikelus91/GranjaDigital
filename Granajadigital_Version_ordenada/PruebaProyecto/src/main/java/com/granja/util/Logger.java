package main.java.com.granja.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_DIRECTORY = "logs";
    private static final String LOG_FILE = LOG_DIRECTORY + File.separator + "granja_log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            inicializarDirectorio();
        } catch (IOException e) {
            System.err.println("Error al inicializar el sistema de logs: " + e.getMessage());
        }
    }

    private static void inicializarDirectorio() throws IOException {
        File directory = new File(LOG_DIRECTORY);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("No se pudo crear el directorio de logs");
        }
    }

    public static void log(String message) {
        String logMessage = String.format("[%s] %s%n", 
            LocalDateTime.now().format(formatter), message);
            
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("Error escribiendo en el archivo de log: " + e.getMessage());
        }
    }

    public static void error(String message, Throwable e) {
        String logMessage = String.format("[%s] ERROR: %s - %s%n", 
            LocalDateTime.now().format(formatter), 
            message,
            e.getMessage());
            
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logMessage);
        } catch (IOException ex) {
            System.err.println("Error escribiendo en el archivo de log: " + ex.getMessage());
        }
    }
}
