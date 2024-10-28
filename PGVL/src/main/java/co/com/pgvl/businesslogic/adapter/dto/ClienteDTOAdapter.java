package co.com.pgvl.businesslogic.adapter.dto;

import co.com.pgvl.businesslogic.adapter.Adapter;
import co.com.pgvl.domain.ClienteDomain;
import co.com.pgvl.dto.ClienteDTO;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public final class ClienteDTOAdapter implements Adapter<ClienteDomain, ClienteDTO>{
	
	private static final Adapter<ClienteDomain, ClienteDTO> instance = new ClienteDTOAdapter();
	
	private ClienteDTOAdapter() {
		
	}
	
	public static Adapter<ClienteDomain, ClienteDTO> getCountryDTOAdapter(){
		return instance;
	}

	@Override
	public ClienteDomain adaptSource(final ClienteDTO data) {
		var dtoToAdapt = ObjectHelper.getDefault(data, ClienteDTO.create());
		return ClienteDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()), data.getNombre());
	}

	@Override
	public ClienteDTO adaptTarget(final ClienteDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data, ClienteDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY));
		return ClienteDTO.create().setId("").setNombre(domainToAdapt.getName());
	}

}
