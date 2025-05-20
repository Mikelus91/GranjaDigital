package main.java.com.granja.service;

import main.java.com.granja.dao.EmpleadoDAO;
import main.java.com.granja.model.Empleado;
import main.java.com.granja.util.Logger;
import main.java.com.granja.exceptions.DatabaseException;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoService {
    private final static EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public static void registrarEmpleado(Empleado empleado) throws DatabaseException {
        try {
            empleadoDAO.crear(empleado);
            Logger.log("Empleado registrado: " + empleado.getNombre());
        } catch (SQLException e) {
            Logger.error("Error al registrar empleado", e);
            throw new DatabaseException("Error al registrar empleado: " + e.getMessage(), e);
        }
    }

    public static List<Empleado> listarEmpleados() throws DatabaseException {
        try {
            return empleadoDAO.listarTodos();
        } catch (SQLException e) {
            Logger.error("Error al listar empleados", e);
            throw new DatabaseException("Error al listar empleados: " + e.getMessage(), e);
        }
    }

    public static void actualizarEmpleado(Empleado empleado) throws DatabaseException {
        try {
            empleadoDAO.actualizar(empleado);
            Logger.log("Empleado actualizado: " + empleado.getNombre());
        } catch (SQLException e) {
            Logger.error("Error al actualizar empleado", e);
            throw new DatabaseException("Error al actualizar empleado: " + e.getMessage(), e);
        }
    }

    public static void eliminarEmpleado(int id) throws DatabaseException {
        try {
            empleadoDAO.eliminar(id);
            Logger.log("Empleado eliminado: ID " + id);
        } catch (SQLException e) {
            Logger.error("Error al eliminar empleado", e);
            throw new DatabaseException("Error al eliminar empleado: " + e.getMessage(), e);
        }
    }

    public static Empleado buscarEmpleadoPorId(int id) throws DatabaseException {
    try {
        return empleadoDAO.buscarPorId(id);
    } catch (SQLException e) {
        Logger.error("Error al buscar empleado por ID", e);
        throw new DatabaseException("Error al buscar empleado: " + e.getMessage(), e);
    }
}

}

