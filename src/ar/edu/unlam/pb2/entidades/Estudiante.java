package ar.edu.unlam.pb2.entidades;

public class Estudiante extends Persona{

	private Integer id;
	
	public Estudiante(Long dni, String nombre, String apellido, String mail, String telefono,Integer id) {
		super(dni, nombre, apellido, mail, telefono);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
