package ar.edu.unlam.pb2.entidades;

import java.util.Set;
import java.util.TreeSet;

public class Estudiante extends Persona{

	private Integer id;
	private Set<Curso> cursosInscritos;

	
	public Estudiante(Long dni, String nombre, String apellido, String mail, String telefono,Integer id) {
		super(dni, nombre, apellido, mail, telefono);
		this.id = id;
		this.cursosInscritos = new TreeSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Curso> getCursosInscritos() {
		return cursosInscritos;
	}

	public void setCursosInscritos(Set<Curso> cursosInscritos) {
		this.cursosInscritos = cursosInscritos;
	}
	
	
	
	
	

}
