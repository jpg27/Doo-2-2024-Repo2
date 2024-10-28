package co.com.pgvl.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

import co.com.pgvl.data.dao.ClienteDAO;
import co.com.pgvl.data.dao.DAOFactory;
import co.edu.uco.crosscutting.helpers.SqlConnectionHelper;

public final class SqlServerDAOFactory extends DAOFactory {
	
	private Connection connection;
	
	public SqlServerDAOFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		SqlConnectionHelper.validateIfConnectionIsOpen(connection);
		var connectionString = "jdbc:sqlserver://ucobet-server.database.windows.net:1433;database=ucobet-db;user=ucobetdbuser;password=uc0b3tdbus3r!;encrypt=true;trustServerCertificate=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			connection = SqlConnectionHelper.openConnection(connectionString);

		
	    /*try {
	        connection = DatabaseConnection.getConnection();
	    } catch (SQLException e) {
	        var userMessage = "Ocurrió un error al intentar abrir la conexión con la base de datos";
	        var technicalMessage = "Error al abrir la conexión con SQL Server: " + e.getMessage();
	        throw DataUcoBetException.crear(userMessage, technicalMessage, e);
	    }*/;
		
	}

	@Override
	public void initTransaction() {
		SqlConnectionHelper.initTransaction(connection);
		
	}

	@Override
	public void commitTramsaction() {
		SqlConnectionHelper.commitTransaction(connection);
		
	}

	@Override
	public void rollbackTransaction() {
		SqlConnectionHelper.rollbackTransaction(connection);
		
	}

	@Override
	public void closeConnection() {
		SqlConnectionHelper.closeConnection(connection);
		
	}

	@Override
	public ClienteDAO getCountryDAO() {
		return new ClienteSqlServerDAO(connection);
	}

}
