package ar.edu.unlam.pb2.dominio;

import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import ar.edu.unlam.pb2.contratos.Plataforma;
import ar.edu.unlam.pb2.entidades.Curso;
import ar.edu.unlam.pb2.entidades.Estudiante;
import ar.edu.unlam.pb2.entidades.Inscripcion;
import ar.edu.unlam.pb2.entidades.Persona;
import ar.edu.unlam.pb2.enums.Idioma;
import ar.edu.unlam.pb2.excepciones.CapacidadMaximaExcedidaException;
import ar.edu.unlam.pb2.excepciones.CursoRepetidaException;
import ar.edu.unlam.pb2.excepciones.EstudianteDuplicadoException;
import ar.edu.unlam.pb2.excepciones.PersonaRepetidaException;

public class PlataformaImpl implements Plataforma{

	
	Set<Persona> personas;
	Set<Curso> cursos;
	List<Inscripcion> inscripciones;
	
	public PlataformaImpl() {
		this.personas = new TreeSet<>();
		this.cursos = new TreeSet<>();
		this.inscripciones = new ArrayList<>();
	}

	@Override
	public Boolean agregarPersona(Persona persona) throws PersonaRepetidaException {
		
		if(personas.contains(persona)) {
			throw new PersonaRepetidaException("Persona ya existe");
		}
		
		return this.personas.add(persona);
	}

	@Override
	public Boolean agregarCurso(Curso curso) throws CursoRepetidaException {
		
		if(cursos.contains(curso)) {
			throw new CursoRepetidaException("Curso ya existe");
		}
		
		return this.cursos.add(curso);
	}

	@Override
	public Boolean agregarInscripcion(Estudiante estudiante, Curso curso) throws EstudianteDuplicadoException, CapacidadMaximaExcedidaException {
		return curso.inscribir(estudiante);
	}

	@Override
	public Curso buscarCursoPorId(Long id) {
		
		for(Curso c : this.cursos) {
			if(c.getId().equals(id)) {
				return c;
			}
		}
		
		return null;
	}

	@Override
	public Set<Estudiante> obtenerEstudiantesEnElCurso() {
		Set<Estudiante> obtenerEstudiantes = new TreeSet<>();
		
		for(Persona p : this.personas) {
			if(p instanceof Estudiante) {
				obtenerEstudiantes.add((Estudiante) p);
			}
		}
		
		return obtenerEstudiantes;
	}

	@Override
	public Set<Curso> obtenerCantidadCursos(Estudiante estudiante) throws CursoRepetidaException {
		Set<Curso> obtenerCursos = new TreeSet<>();
		
		
		for(Curso c : this.cursos) {
			if(c.estaInscripto(estudiante)) {
				obtenerCursos.add(c);
			}
			
		}
		
		return obtenerCursos;
	}

	@Override
	public Set<Curso> obtenerCursosPorIdioma(Idioma idioma) {
		Set<Curso> obtenerCursos = new TreeSet<>();
		
		for(Curso c : this.cursos) {
			if(c.getIdiomaCurso().equals(idioma)) {
				obtenerCursos.add(c);
			}
		}
		
		return obtenerCursos;
	}

	@Override
	public Curso obtenerCursoMasLargo() {
		
			if(cursos == null || cursos.isEmpty()) {
				return null;
			}
			
			Optional<Curso> cursoMasLargo = cursos.stream().max(Comparator.comparingInt(Curso::getHoras));
			
			
			return cursoMasLargo.orElse(null);
		
	}

	@Override
	public Curso obtenerCursoMasCaro() {
		
		if(cursos == null || cursos.isEmpty()) {
			return null;
		}
		
		Optional<Curso> cursoMasCaro = cursos.stream().max(Comparator.comparingDouble(Curso::getPrecio));
		
		
		return cursoMasCaro.orElse(null);
	}

	@Override
	public Double calcularPromedioDePrecioCursos() {
		
		
		if(cursos.isEmpty()) {
			return 0.0;
		}
		
		Double sum = 0.0;
		
		for(Curso c : this.cursos) {
			sum  += c.getPrecio();
		}
		
		Double promedio = sum / cursos.size();
		
		return promedio;
	}
	
	
	
}
