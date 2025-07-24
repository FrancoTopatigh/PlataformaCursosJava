package ar.edu.unlam.pb2.entidades;

import java.util.Set;
import java.util.TreeSet;

import ar.edu.unlam.pb2.enums.Idioma;
import ar.edu.unlam.pb2.excepciones.CapacidadMaximaExcedidaException;
import ar.edu.unlam.pb2.excepciones.EstudianteDuplicadoException;

public abstract class Curso implements Comparable<Curso>{

	Set<Estudiante> estudiantes;
	
	private Long id;
	private String nombre;
	private Double precio;
	private Integer cantidadEstudiantes;
	private Idioma idiomaCurso;
	private Integer horas;
	private Profesor profesor;
	
	public Curso(Long id, String nombre, Double precio, Integer cantidadEstudiantes, Idioma idiomaCurso,Integer horas, Profesor profesor) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidadEstudiantes = cantidadEstudiantes;
		this.idiomaCurso = idiomaCurso;
		this.horas = horas;
		this.profesor = profesor;
		this.estudiantes = new TreeSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantidadEstudiantes() {
		return cantidadEstudiantes;
	}

	public void setCantidadEstudiantes(Integer cantidadEstudiantes) {
		this.cantidadEstudiantes = cantidadEstudiantes;
	}

	public Idioma getIdiomaCurso() {
		return idiomaCurso;
	}

	public void setIdiomaCurso(Idioma idiomaCurso) {
		this.idiomaCurso = idiomaCurso;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	@Override
	public int compareTo(Curso o) {
		return this.id.compareTo(o.id);
	}

	public Boolean inscribir(Estudiante estudiante) throws EstudianteDuplicadoException, CapacidadMaximaExcedidaException {
		if(estudiantes.contains(estudiante)) {
			throw new EstudianteDuplicadoException("Estudiante ya esta agregado al curso");
		}
			
		if(estudiantes.size() >= this.getCantidadEstudiantes()) {
			throw new CapacidadMaximaExcedidaException("Capacidad maxima de curso fue excedida");
		}
		
		return estudiantes.add(estudiante);
	}

	public Boolean estaInscripto(Estudiante estudiante) {
		return estudiantes.contains(estudiante);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Curso other = (Curso) obj;
	    return this.id != null && this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
	    return id != null ? id.hashCode() : 0;
	}

	
}
