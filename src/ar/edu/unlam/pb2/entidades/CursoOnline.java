package ar.edu.unlam.pb2.entidades;

import ar.edu.unlam.pb2.enums.Idioma;

public class CursoOnline extends Curso{

	private String aulaVirtual;
	private String contraseniaAulaVirtual;
	
	public CursoOnline(Long id, String nombre, Double precio, Integer cantidadEstudiantes, Idioma idiomaCurso,
			Integer horas, Profesor profesor, String aulaVirtual, String contraseniaAulaVirtual) {
		super(id, nombre, precio, cantidadEstudiantes, idiomaCurso, horas, profesor);
		this.aulaVirtual = aulaVirtual;
		this.contraseniaAulaVirtual = contraseniaAulaVirtual;
	}

	public String getAulaVirtual() {
		return aulaVirtual;
	}

	public void setAulaVirtual(String aulaVirtual) {
		this.aulaVirtual = aulaVirtual;
	}

	public String getContraseniaAulaVirtual() {
		return contraseniaAulaVirtual;
	}

	public void setContraseniaAulaVirtual(String contraseniaAulaVirtual) {
		this.contraseniaAulaVirtual = contraseniaAulaVirtual;
	}

	
	
	
}
