package main.java.com.granja.service;

import main.java.com.granja.dao.AnimalDAO;
import main.java.com.granja.exceptions.DatabaseException;
import main.java.com.granja.model.Animal;
import main.java.com.granja.util.Logger;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {
    private static final AnimalDAO animalDAO = new AnimalDAO();

    public void registrarAnimal(Animal animal) throws SQLException, DatabaseException {
        try {
            animalDAO.crear(animal);
            Logger.log("Animal registrado: " + animal.getIdentificador());
        } catch (SQLException e) {
            Logger.error("Error al registrar animal", e);
            throw e;
        }
    }

    public List<Animal> listarAnimales() throws SQLException, DatabaseException {
        return animalDAO.listarTodos();
    }

    public Animal buscarAnimalPorId(int id) throws SQLException, DatabaseException {
        return animalDAO.buscarPorId(id);
    }

    public void actualizarAnimal(Animal animal) throws SQLException, DatabaseException {
        animalDAO.actualizar(animal);
        Logger.log("Animal actualizado: " + animal.getIdentificador());
    }

    public boolean eliminarAnimal(int id) throws SQLException, DatabaseException {
        int filasAfectadas = animalDAO.eliminar(id);
        Logger.log("Animal eliminado: ID " + id);
        return filasAfectadas > 0;
    }
}