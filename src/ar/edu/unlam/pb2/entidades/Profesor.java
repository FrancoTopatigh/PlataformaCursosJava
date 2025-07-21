package ar.edu.unlam.pb2.entidades;

public class Profesor extends Persona{

	private Long legajo;
	
	public Profesor(Long dni, String nombre, String apellido, String mail, String telefono, Long legajo) {
		super(dni, nombre, apellido, mail, telefono);
		this.legajo = legajo;
	}

	public Long getLegajo() {
		return legajo;
	}

	public void setLegajo(Long legajo) {
		this.legajo = legajo;
	}

	
	
}
