package co.edu.uco.ucobet.businesslogic.usecase.city.impl;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.ucobet.businesslogic.usecase.city.RegisterNewCity;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.CityNameConsistencyIsValid;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.CityNameDoesNotExistForState;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.impl.CityNameConsistencyIsValidImpl;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.impl.CityNameDoesNotExistsForStateImpl;
import co.edu.uco.ucobet.businesslogic.usecase.state.rules.StateExists;
import co.edu.uco.ucobet.businesslogic.usecase.state.rules.impl.StateExistsImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;

public final class RegisterNewCityImpl implements RegisterNewCity {
	
	private DAOFactory daoFactory;
	private CityNameDoesNotExistForState cityNameDoesNotExistForState = new CityNameDoesNotExistsForStateImpl();
	private StateExists stateExists = new StateExistsImpl();
	private CityNameConsistencyIsValid cityNameConsistencyIsValid = new CityNameConsistencyIsValidImpl();	
	public RegisterNewCityImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}

	@Override
	public void execute(final CityDomain data) {
		//validar politicas
		cityNameConsistencyIsValid.execute(data.getName());
		cityNameDoesNotExistForState.execute(data, daoFactory);
		stateExists.execute(data.getState().getId(), daoFactory);
		
		var cityDomainToMap = CityDomain.create(generateId(), data.getName(), data.getState());
		var CityEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(cityDomainToMap);
		daoFactory.getCityDAO().create(CityEntity);
		
	}
	
	private UUID generateId() {
		var id = UUIDHelper.generate();
		var cityEntity = daoFactory.getCityDAO().findById(id);
		
		if(UUIDHelper.isEqual(cityEntity.getId(), id)){
			id = generateId();
		}
		return id;
	}
	
	private void setDaoFactory(final DAOFactory daoFactory) {
		if(ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo el registro de la informacion de la ciudad deseada, por favor ontente de nuevo y si el problema persiste llame a Luz Mery Rios";
			var technicalMessage = "El dao factory requerido para crear la clase que actualiza la ciudad llego nula";
			throw BusinessLogicUcoBetException.crear(userMessage, technicalMessage);
		}
		
		this.daoFactory = daoFactory;
	}

}
