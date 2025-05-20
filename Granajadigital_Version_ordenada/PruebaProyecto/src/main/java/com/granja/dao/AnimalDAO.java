package main.java.com.granja.dao;

import main.java.com.granja.config.DatabaseConfig;
import main.java.com.granja.exceptions.DatabaseException;
import main.java.com.granja.model.Animal;
import main.java.com.granja.model.Animal.EstadoSalud;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    public void crear(Animal animal) throws SQLException, DatabaseException {
        String sql = "INSERT INTO animales (identificador, especie, raza, fecha_nacimiento, estado_salud, ubicacion, estado) VALUES (?, ?, ?, ?, ?, ?, 'ACTIVO')";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, animal.getIdentificador());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaza());
            stmt.setDate(4, Date.valueOf(animal.getFechaNacimiento()));
            stmt.setString(5, animal.getEstadoSalud().name());
            stmt.setString(6, animal.getUbicacion());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    animal.setId(rs.getInt(1));
                }
            }
        }
    }

    public Animal buscarPorId(int id) throws SQLException, DatabaseException {
        String sql = "SELECT * FROM animales WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return convertirResultSetAAnimal(rs);
                }
            }
        }
        return null;
    }

    public List<Animal> listarTodos() throws SQLException, DatabaseException {
        List<Animal> animales = new ArrayList<>();
        String sql = "SELECT * FROM animales WHERE estado = 'ACTIVO'";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                animales.add(convertirResultSetAAnimal(rs));
            }
        }
        return animales;
    }

    public void actualizar(Animal animal) throws SQLException, DatabaseException {
        String sql = "UPDATE animales SET estado_salud = ?, ubicacion = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, animal.getEstadoSalud().name());
            stmt.setString(2, animal.getUbicacion());
            stmt.setInt(3, animal.getId());
            
            stmt.executeUpdate();
        }
    }

    public int eliminar(int id) throws SQLException, DatabaseException {
    String sql = "UPDATE animales SET estado = 'FALLECIDO' WHERE id = ?";
    
    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        return stmt.executeUpdate(); // Devuelve el número de filas afectadas
        }
    }


    private Animal convertirResultSetAAnimal(ResultSet rs) throws SQLException {
        Animal animal = new Animal(
            rs.getString("identificador"),
            rs.getString("especie"),
            rs.getString("raza"),
            rs.getDate("fecha_nacimiento").toLocalDate(),
            EstadoSalud.valueOf(rs.getString("estado_salud")),
            rs.getString("ubicacion")
        );
        animal.setId(rs.getInt("id"));
        return animal;
    }
}
