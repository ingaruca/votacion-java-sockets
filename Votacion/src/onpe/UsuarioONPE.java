package onpe;

public class UsuarioONPE {
	
	private String dni;
	private String lugar;
	private String miembro;
	private String direccion;
	private String ubicacion;
	private String nroMesa;
	
	public UsuarioONPE() {
		
	}
	
	public UsuarioONPE(String dni, String lugar, String miembro, String direccion, String ubicacion, String nroMesa) {		
		this.dni = dni;
		this.lugar = lugar;
		this.miembro = miembro;
		this.direccion = direccion;
		this.ubicacion = ubicacion;
		this.nroMesa = nroMesa;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getMiembro() {
		return miembro;
	}

	public void setMiembro(String miembro) {
		this.miembro = miembro;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getNroMesa() {
		return nroMesa;
	}

	public void setNroMesa(String nroMesa) {
		this.nroMesa = nroMesa;
	}
	

}
