package co.com.pgvl.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ClienteEntity extends DomainEntity{
	
	private String nombre;
	private String correo;
	private String direccion;
	private String numeroDocumento;
	private String numeroLicencia;
	private String tipoDocumento;
	private String celular;
	
	public ClienteEntity() {
		super(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
		setCorreo(TextHelper.EMPTY);
		setDireccion(TextHelper.EMPTY);
		setNumeroDocumento(TextHelper.EMPTY);
		setNumeroLicencia(TextHelper.EMPTY);
		setTipoDocumento(TextHelper.EMPTY);
		setCelular(TextHelper.EMPTY);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	
	@Override
	public UUID getId() {
		return super.getId();
	}
	
}
