package co.edu.uco.ucobet.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucobet.crosscutting.exceptions.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.CityDAO;
import co.edu.uco.ucobet.data.dao.impl.sql.SqlDAO;
import co.edu.uco.ucobet.entity.CityEntity;

final class CitySqlServerDAO extends SqlDAO implements CityDAO {

   protected CitySqlServerDAO(Connection connection) {
		super(connection);
	}

    //private static final String INSERT_CITY_QUERY = "INSERT INTO City (id, name, stateId) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, stateId FROM City WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name, stateId FROM City";
    private static final String FIND_BY_FILTER_QUERY = "SELECT id, name, stateId FROM City WHERE name LIKE ?";
   // private static final String UPDATE_CITY_QUERY = "UPDATE City SET name = ?, stateId = ? WHERE id = ?";
    //private static final String DELETE_CITY_QUERY = "DELETE FROM City WHERE id = ?";

    @Override
    public void create(final CityEntity data) {
    	
    	final StringBuilder statement = new StringBuilder();
    	statement.append("INSERT INTO City(id, name, state) VALUES (?, ?, ?)");
    	
    	try(final var preparedStatement = getConnection().prepareStatement(statement.toString())){
    		
    		preparedStatement.setObject(1, data.getId());
    		preparedStatement.setString(2, data.getName());
    		preparedStatement.setObject(3, data.getState().getId());
    		
    		preparedStatement.executeUpdate();
    		
    	} catch (final SQLException exception) {
    		var userMessage = "Se ha presentado un problema tratando de llevar a cabo el registro de la informacion del nuevo pais, por favor intente de nuevo y si el problema persiste reporte la novedad";
			var technicalMessage = "Se ha presentado un problema al tratar de registrar la infomracion del nuevo pais en la base de datos sql server, por favor valide el log de errores para encontrar mayores detalles del problema presentado";

			throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
		}
    	
    	
       /* try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CITY_QUERY)) {

            statement.setString(1, data.getId().toString());
            statement.setString(2, data.getName());
            statement.setString(3, data.getId().toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            logError(e, "Error al crear la ciudad: " + data);
            throw new RuntimeException("Error al crear la ciudad", e);
        }*/
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
    public void update(final CityEntity data) {
    	
    	final StringBuilder statement = new StringBuilder();
    	statement.append("UPDATE CITY SET name = ?, state = ?");
    	
    	try(final var preparedStatement = getConnection().prepareStatement(statement.toString())){
    		
    		preparedStatement.setString(1, data.getName());
    		preparedStatement.setObject(2, data.getState().getId());
    		preparedStatement.setObject(3, data.getId());
    		
    		preparedStatement.executeUpdate();
    		
    	} catch (final SQLException exception) {
    		var userMessage = "Se ha presentado un problema tratando de llevar a cabo el cambio de la informacion del nuevo pais, por favor intente de nuevo y si el problema persiste reporte la novedad";
			var technicalMessage = "Se ha presentado un problema al tratar de modificar la infomracion del nuevo pais en la base de datos sql server, por favor valide el log de errores para encontrar mayores detalles del problema presentado";

			throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
		}
    	
    	
    	
        /*try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CITY_QUERY)) {

            statement.setString(1, data.getName());
            statement.setString(2, data.getId().toString());
            statement.setString(3, data.getId().toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            logError(e, "Error al actualizar la ciudad: " + data);
            throw new RuntimeException("Error al actualizar la ciudad", e);
        }*/
    }

    @Override
    public void delete(final UUID data) {
    	
    	final StringBuilder statement = new StringBuilder();
    	statement.append("DELETE FROM City WHERE id = ?");
    	
    	try(final var preparedStatement = getConnection().prepareStatement(statement.toString())){
    		
    		preparedStatement.setObject(1, data);
    		
    		preparedStatement.executeUpdate();
    		
    	} catch (final SQLException exception) {
    		var userMessage = "Se ha presentado un problema tratando de llevar a cabo la eliminacion la informacion del nuevo pais, por favor intente de nuevo y si el problema persiste reporte la novedad";
			var technicalMessage = "Se ha presentado un problema al tratar de eliminar la infomracion del nuevo pais en la base de datos sql server, por favor valide el log de errores para encontrar mayores detalles del problema presentado";

			throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
		}
    }
    	
    	
        /*try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CITY_QUERY)) {

            statement.setString(1, id.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            logError(e, "Error al eliminar la ciudad con id: " + id);
            throw new RuntimeException("Error al eliminar la ciudad", e);
        }
    }*/

    private CityEntity mapResultSetToCityEntity(ResultSet resultSet) throws SQLException {
        return new CityEntity();
    }

    //para que suba al git <3
    private void logError(SQLException e, String message) {
        System.err.println(message);
        e.printStackTrace();
    }
   }

