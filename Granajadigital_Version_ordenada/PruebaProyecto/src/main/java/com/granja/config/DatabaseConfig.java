package main.java.com.granja.config;

import main.java.com.granja.exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://127.0.0.1:3307/granja_digital";
    private static final String USER = "granja_user";
    private static final String PASSWORD = "Granja1234";
    private static Connection connection = null;

    public static Connection getConnection() throws DatabaseException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Error de driver MySQL: " + e.getMessage());
        } catch (SQLException e) {
            throw new DatabaseException("Error de conexión: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}