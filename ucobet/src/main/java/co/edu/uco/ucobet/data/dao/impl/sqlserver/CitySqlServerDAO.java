package co.edu.uco.ucobet.data.dao.impl.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucobet.data.dao.CityDAO;
import co.edu.uco.ucobet.entity.CityEntity;

public class CitySqlServerDAO implements CityDAO {

    private static final String INSERT_CITY_QUERY = "INSERT INTO City (id, name, stateId) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, stateId FROM City WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name, stateId FROM City";
    private static final String FIND_BY_FILTER_QUERY = "SELECT id, name, stateId FROM City WHERE name LIKE ?";
    private static final String UPDATE_CITY_QUERY = "UPDATE City SET name = ?, stateId = ? WHERE id = ?";
    private static final String DELETE_CITY_QUERY = "DELETE FROM City WHERE id = ?";

    @Override
    public void create(CityEntity data) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CITY_QUERY)) {

            statement.setString(1, data.getId().toString());
            statement.setString(2, data.getName());
            statement.setString(3, data.getId().toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            logError(e, "Error al crear la ciudad: " + data);
            throw new RuntimeException("Error al crear la ciudad", e);
        }
    }

    @Override
    public CityEntity findById(UUID id) {
        CityEntity city = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {

            statement.setString(1, id.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = mapResultSetToCityEntity(resultSet);
                }
            }

        } catch (SQLException e) {
            logError(e, "Error al buscar la ciudad con id: " + id);
            throw new RuntimeException("Error al buscar la ciudad con id: " + id, e);
        }

        return city;
    }

    @Override
    public List<CityEntity> findAll() {
        List<CityEntity> cities = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                cities.add(mapResultSetToCityEntity(resultSet));
            }

        } catch (SQLException e) {
            logError(e, "Error al buscar todas las ciudades.");
            throw new RuntimeException("Error al buscar todas las ciudades", e);
        }

        return cities;
    }

    @Override
    public List<CityEntity> findByFilter(CityEntity filter) {
        List<CityEntity> cities = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_FILTER_QUERY)) {

            statement.setString(1, "%" + filter.getName() + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(mapResultSetToCityEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            logError(e, "Error al buscar ciudades con filtro: " + filter);
            throw new RuntimeException("Error al buscar ciudades con filtro", e);
        }

        return cities;
    }

    @Override
    public void update(CityEntity data) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CITY_QUERY)) {

            statement.setString(1, data.getName());
            statement.setString(2, data.getId().toString());
            statement.setString(3, data.getId().toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            logError(e, "Error al actualizar la ciudad: " + data);
            throw new RuntimeException("Error al actualizar la ciudad", e);
        }
    }

    @Override
    public void delete(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CITY_QUERY)) {

            statement.setString(1, id.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            logError(e, "Error al eliminar la ciudad con id: " + id);
            throw new RuntimeException("Error al eliminar la ciudad", e);
        }
    }

    private CityEntity mapResultSetToCityEntity(ResultSet resultSet) throws SQLException {
        return new CityEntity(
        );
    }

    //para que suba al git <3
    private void logError(SQLException e, String message) {
        System.err.println(message);
        e.printStackTrace();
    }
}

