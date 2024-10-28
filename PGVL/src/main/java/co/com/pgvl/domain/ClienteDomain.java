package co.com.pgvl.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ClienteDomain extends Domain{
	
	private String name;
	
	private ClienteDomain(final UUID id, final String name) {
		super(id);
		setName(name);
	}
	
	public static final ClienteDomain create(final UUID id, final String name) {
		return new ClienteDomain(id, name);
	}
	
	static final ClienteDomain create() {
		return new ClienteDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
	}
	
	public String getName() {
		return name;
	}

	private void setName(final String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	@Override
	public UUID getId() {
		return super.getId();
	}

}
