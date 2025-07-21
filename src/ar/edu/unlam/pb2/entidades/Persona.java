package ar.edu.unlam.pb2.entidades;

public abstract class Persona implements Comparable<Persona>{

	private Long dni;
	private String nombre;
	private String apellido;
	private String mail;
	private String telefono;
	
	public Persona(Long dni, String nombre, String apellido, String mail, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.telefono = telefono;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public int compareTo(Persona o) {
		return this.dni.compareTo(o.dni);
	}
	
	
	
}
