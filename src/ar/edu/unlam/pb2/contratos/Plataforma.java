package ar.edu.unlam.pb2.contratos;

import java.util.Set;

import ar.edu.unlam.pb2.entidades.Curso;
import ar.edu.unlam.pb2.entidades.Estudiante;
import ar.edu.unlam.pb2.entidades.Persona;
import ar.edu.unlam.pb2.excepciones.CapacidadMaximaExcedidaException;
import ar.edu.unlam.pb2.excepciones.CursoRepetidaException;
import ar.edu.unlam.pb2.excepciones.EstudianteDuplicadoException;
import ar.edu.unlam.pb2.excepciones.PersonaRepetidaException;

public interface Plataforma {

	Boolean agregarPersona(Persona persona) throws PersonaRepetidaException;
	Boolean agregarCurso(Curso curso) throws CursoRepetidaException;
	Boolean agregarInscripcion(Estudiante estudiante, Curso curso) throws PersonaRepetidaException, EstudianteDuplicadoException, CapacidadMaximaExcedidaException;
	Curso buscarCursoPorId(Long id);
	Set<Estudiante> obtenerEstudiantesEnElCurso();
	Set<Curso> obtenerCantidadCursos(Estudiante estudiante) throws CursoRepetidaException;
	
}
