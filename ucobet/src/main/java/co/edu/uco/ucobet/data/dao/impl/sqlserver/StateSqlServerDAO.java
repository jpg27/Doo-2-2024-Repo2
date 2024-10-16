package co.edu.uco.ucobet.data.dao.impl.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucobet.data.dao.StateDAO;
import co.edu.uco.ucobet.entity.StateEntity;

public class StateSqlServerDAO implements StateDAO {

    private static final String FIND_BY_ID_QUERY = "SELECT id, name FROM State WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name FROM State";
    private static final String FIND_BY_FILTER_QUERY = "SELECT id, name FROM State WHERE name LIKE ?";

    @Override
    public StateEntity findById(UUID id) {
        StateEntity state = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {

            statement.setString(1, id.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    state = mapResultSetToStateEntity(resultSet);
                }
            }

        } catch (SQLException e) {
            logError(e, "Error al buscar el estado con id: " + id);
            throw new RuntimeException("Error al buscar el estado con id: " + id, e);
        }

        return state;
    }

    @Override
    public List<StateEntity> findAll() {
        List<StateEntity> states = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                states.add(mapResultSetToStateEntity(resultSet));
            }

        } catch (SQLException e) {
            logError(e, "Error al buscar todos los estados.");
            throw new RuntimeException("Error al buscar todos los estados", e);
        }

        return states;
    }

    @Override
    public List<StateEntity> findByFilter(StateEntity filter) {
        List<StateEntity> states = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_FILTER_QUERY)) {

            statement.setString(1, "%" + filter.getName() + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    states.add(mapResultSetToStateEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            logError(e, "Error al buscar estados con filtro: " + filter);
            throw new RuntimeException("Error al buscar estados con filtro", e);
        }

        return states;
    }

    private StateEntity mapResultSetToStateEntity(ResultSet resultSet) throws SQLException {
        return new StateEntity(
        );
    }

    private void logError(SQLException e, String message) {
        System.err.println(message);
        e.printStackTrace();
    }
}

