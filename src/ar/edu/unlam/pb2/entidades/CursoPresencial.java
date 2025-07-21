package ar.edu.unlam.pb2.entidades;

import ar.edu.unlam.pb2.enums.Idioma;

public class CursoPresencial extends Curso {
	
	
	private String aula;

	public CursoPresencial(Long id, String nombre, Double precio, Integer cantidadEstudiantes, Idioma idiomaCurso,
			Integer horas, Profesor profesor, String aula) {
		super(id, nombre, precio, cantidadEstudiantes, idiomaCurso, horas, profesor);
		this.aula = aula;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}
	
	

	

	
	
	

}
