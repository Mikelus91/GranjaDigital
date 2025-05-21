package main.java.com.granja.service;

import main.java.com.granja.dao.ActividadDAO;
import main.java.com.granja.model.Actividad;
import main.java.com.granja.util.Logger;
import java.util.List;

public class ActividadService {
    private final static ActividadDAO actividadDAO = new ActividadDAO();

    public void registrarActividad(Actividad actividad) {
        try {
            actividadDAO.crear(actividad);
        } catch (Exception e) {
            Logger.error("Error al registrar actividad", e);
            throw e;
        }
    }

    public Actividad buscarPorId(int id) {
        try {
            return actividadDAO.obtenerPorId(id);
        } catch (Exception e) {
            Logger.error("Error al buscar actividad por ID: " + id, e);
            throw new RuntimeException("Error al buscar actividad: " + e.getMessage());
        }
    }

    public List<Actividad> listarActividades() {
        try {
            return actividadDAO.obtenerTodos();
        } catch (Exception e) {
            Logger.error("Error al listar actividades", e);
            throw e;
        }
    }

    public void actualizarActividad(Actividad actividad) {
        try {
            actividadDAO.actualizar(actividad);
        } catch (Exception e) {
            Logger.error("Error al actualizar actividad", e);
            throw e;
        }
    }

    public boolean eliminarActividad(int id) {
    try {
        return actividadDAO.eliminar(id);
    } catch (Exception e) {
        Logger.error("Error al eliminar actividad", e);
        throw e;
    }
}

}