package ar.edu.unlam.pb2.entidades;

public class Inscripcion{

	public Long id;
	public Estudiante estudiantes;
	public Curso cursos;
	
	public Inscripcion(Long id, Estudiante estudiantes, Curso cursos) {
		this.id = id;
		this.estudiantes = estudiantes;
		this.cursos = cursos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estudiante getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(Estudiante estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Curso getCursos() {
		return cursos;
	}

	public void setCursos(Curso cursos) {
		this.cursos = cursos;
	}
	

	
	
	
}
