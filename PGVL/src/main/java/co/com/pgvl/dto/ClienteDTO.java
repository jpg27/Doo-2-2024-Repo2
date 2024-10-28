package co.com.pgvl.dto;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ClienteDTO extends DomainDTO{
	
	private String nombre;
	private String correo;
	private String direccion;
	private String numeroDocumento;
	private String numeroLicencia;
	private String tipoDocumento;
	private String celular;
	
	public ClienteDTO() {
		super(UUIDHelper.getDefaultAsString());
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

	public ClienteDTO setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
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
	public String getId() {
		return super.getId();
	}
	
	public ClienteDTO setId(final String id) {
		super.setIdentifier(id);
		return this;
	}
	
	
	public static final ClienteDTO create() {
		return new ClienteDTO();
	}
	/*

	public String getName() {
		return name;
	}

	public CountryDTO setName(final String name) {
		this.name = TextHelper.applyTrim(name);
		return this;
	}
	

	

	*/
}
