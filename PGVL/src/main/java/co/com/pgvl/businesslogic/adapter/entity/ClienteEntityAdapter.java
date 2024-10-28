package co.com.pgvl.businesslogic.adapter.entity;

import co.com.pgvl.businesslogic.adapter.Adapter;
import co.com.pgvl.domain.ClienteDomain;
import co.com.pgvl.entity.ClienteEntity;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ClienteEntityAdapter implements Adapter<ClienteDomain, ClienteEntity> {
	
	private static final Adapter<ClienteDomain, ClienteEntity> instance = new ClienteEntityAdapter();
	
	private ClienteEntityAdapter() {
		
	}
	
	public static Adapter<ClienteDomain, ClienteEntity> getCountryEntityAdapter(){
		return instance;
	}

	@Override
	public ClienteDomain adaptSource(final ClienteEntity data) {
		var entityToAdapt = ObjectHelper.getDefault(data, new ClienteEntity());
		return ClienteDomain.create(entityToAdapt.getId(), entityToAdapt.getNombre());

	}

	@Override
	public ClienteEntity adaptTarget(final ClienteDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data, ClienteDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY));
		var entityAdapted = new ClienteEntity();
		entityAdapted.setId(domainToAdapt.getId());
		entityAdapted.setNombre(domainToAdapt.getName());
		
		return entityAdapted;
	}

}
