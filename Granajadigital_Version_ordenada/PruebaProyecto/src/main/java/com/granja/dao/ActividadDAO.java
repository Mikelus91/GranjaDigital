package main.java.com.granja.dao;

import main.java.com.granja.model.Actividad;
import main.java.com.granja.util.Logger;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {
    private static final List<Actividad> actividades = new ArrayList<>();
    private static int nextId = 1;

    public void crear(Actividad actividad) {
        actividad.setId(nextId++);
        actividades.add(actividad);
        Logger.log("Actividad creada con ID: " + actividad.getId());
    }

    public Actividad obtenerPorId(int id) {
        return actividades.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Actividad> obtenerTodos() {
        return new ArrayList<>(actividades);
    }

    public void actualizar(Actividad actividad) {
        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getId() == actividad.getId()) {
                actividades.set(i, actividad);
                Logger.log("Actividad actualizada con ID: " + actividad.getId());
                return;
            }
        }
    }

    public boolean eliminar(int id) {
    boolean eliminado = actividades.removeIf(a -> a.getId() == id);
    if (eliminado) {
        Logger.log("Actividad eliminada con ID: " + id);
    } else {
        Logger.log("Intento de eliminar actividad con ID inexistente: " + id);
    }
    return eliminado;
}

}