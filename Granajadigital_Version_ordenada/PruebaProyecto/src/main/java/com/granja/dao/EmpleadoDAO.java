package main.java.com.granja.dao;

import main.java.com.granja.config.DatabaseConfig;
import main.java.com.granja.model.Empleado;
import main.java.com.granja.model.Empleado.Rol;
import main.java.com.granja.exceptions.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    
    public void crear(Empleado empleado) throws SQLException, DatabaseException {
        String sql = "INSERT INTO empleados (nombre, rol, telefono, fecha_contratacion) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getRol().name());
            stmt.setString(3, empleado.getTelefono());
            stmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    empleado.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Empleado> listarTodos() throws SQLException, DatabaseException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE activo = 1";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                empleados.add(convertirResultSetAEmpleado(rs));
            }
        }
        return empleados;
    }

    public void actualizar(Empleado empleado) throws SQLException, DatabaseException {
        String sql = "UPDATE empleados SET nombre = ?, rol = ?, telefono = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getRol().name());
            stmt.setString(3, empleado.getTelefono());
            stmt.setInt(4, empleado.getId());
            
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException, DatabaseException {
        String sql = "UPDATE empleados SET activo = 0 WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Empleado convertirResultSetAEmpleado(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado(
            rs.getString("nombre"),
            Rol.valueOf(rs.getString("rol")),
            rs.getString("telefono"),
            rs.getDate("fecha_contratacion").toLocalDate()
        );
        empleado.setId(rs.getInt("id"));
        empleado.setActivo(rs.getBoolean("activo"));
        return empleado;
    }


    public Empleado buscarPorId(int id) throws SQLException, DatabaseException {
    String sql = "SELECT * FROM empleados WHERE id = ? AND activo = 1";

    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return convertirResultSetAEmpleado(rs);
            } else {
                return null; // No encontrado
            }
        }
    }
}
}

