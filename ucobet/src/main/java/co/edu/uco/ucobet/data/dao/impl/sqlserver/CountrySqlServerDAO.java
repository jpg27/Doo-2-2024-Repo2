package co.edu.uco.ucobet.data.dao.impl.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import co.edu.uco.ucobet.data.dao.CountryDAO;
import co.edu.uco.ucobet.entity.CountryEntity;

public class CountrySqlServerDAO implements CountryDAO {

    private static final String FIND_BY_ID_QUERY = "SELECT id, name FROM Country WHERE id = ?";

    @Override
    public CountryEntity findById(UUID id) {
        CountryEntity country = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {

            statement.setString(1, id.toString());

            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    country = mapResultSetToCountryEntity(resultSet);
                }
            }

        } catch (SQLException e) {
            logError(e, id); 
            throw new RuntimeException("Error al buscar el país con id: " + id, e);
        }

        return country;
    }

    private CountryEntity mapResultSetToCountryEntity(ResultSet resultSet) throws SQLException {
        return new CountryEntity(
        );
    }

 
    private void logError(SQLException e, UUID id) {
        System.err.println("Error al consultar el país con id: " + id);
        e.printStackTrace();
    }

	@Override
	public List<CountryEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CountryEntity> findByFilter(CountryEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}
}

