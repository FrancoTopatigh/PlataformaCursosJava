package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.pb2.entidades.*;
import ar.edu.unlam.pb2.enums.Idioma;
import ar.edu.unlam.pb2.excepciones.CapacidadMaximaExcedidaException;
import ar.edu.unlam.pb2.excepciones.CursoRepetidaException;
import ar.edu.unlam.pb2.excepciones.EstudianteDuplicadoException;
import ar.edu.unlam.pb2.excepciones.PersonaRepetidaException;

public class PlataformaTest {

	public PlataformaImpl plataforma;
	
	@Before
	public void init() {
		this.plataforma = new PlataformaImpl();
	}
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosPuedoAgregarUnEstudiante() throws PersonaRepetidaException {
		Persona estudiante = new Estudiante(42890136L, "Martin", "Lopez", "martincito@gmail.com", "01190821284", 1);
		Boolean agregarEstudiante = plataforma.agregarPersona(estudiante);
		assertTrue(agregarEstudiante);
	}
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosPuedoAgregarUnProfesor() throws PersonaRepetidaException {
		Persona profesor = new Profesor(30982401L, "Diego", "Benedetto", "diegobenedetto@hotmail.com", "01132016549", 17829L);
		Boolean agregarProfesor = plataforma.agregarPersona(profesor);
		assertTrue(agregarProfesor);
	}
	
	@Test(expected = PersonaRepetidaException.class)
	public void dadoQueTengoUnaPlataformaDeCursosNoPuedoAgregar2AlumnosConElMismoDni() throws PersonaRepetidaException {
		Persona estudiante = new Estudiante(42890136L, "Martin", "Lopez", "martincito@gmail.com", "01190821284", 1);
		Persona estudianteRepetido = new Estudiante(42890136L, "Santiago", "Machuca", "santigod@outlook.com", "01126560197", 2);
		plataforma.agregarPersona(estudiante);
		plataforma.agregarPersona(estudianteRepetido);

	}
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosPuedoInscribirEstudiantesACursos() throws PersonaRepetidaException, CursoRepetidaException, EstudianteDuplicadoException, CapacidadMaximaExcedidaException {
		Estudiante estudiante = new Estudiante(42890136L, "Martin", "Lopez", "martincito@gmail.com", "01190821284", 1);
		Profesor profesorJuan = new Profesor(29731216L, "Juan", "Ibarra", "ibarrajuan@gmail.com", "01192354101", 145780L);
		Curso cursoIngles = new CursoPresencial(1L, "Curso Ingles B1", 40000.0, 3, Idioma.INGLES, 4,profesorJuan, "Aula 3");
		
		plataforma.agregarPersona(estudiante);
		plataforma.agregarPersona(profesorJuan);
		plataforma.agregarCurso(cursoIngles);
		
		Boolean agregarInscripcion = plataforma.agregarInscripcion(estudiante, cursoIngles);
		assertTrue(agregarInscripcion);
	}
	
	@Test(expected = EstudianteDuplicadoException.class)
	public void dadoQueTengoUnaPlataformaDeCursosNoPuedoInscribir2VecesAlMismoEstudianteEnElCurso() throws PersonaRepetidaException, CursoRepetidaException, EstudianteDuplicadoException, CapacidadMaximaExcedidaException {
		Estudiante estudiante = new Estudiante(44290519L, "Lucas", "Maggio", "lukitasmaggio@hotmail.com", "01126520394", 2);
		Profesor profesorAlberto = new Profesor(28257216L, "Alberto", "Bioy", "bioyalbert@gmail.com", "01124384221", 142482L);
		Curso cursoFrances = new CursoOnline(1L, "Curso Frances A2", 50000.0, 3, Idioma.FRANCES, 4, profesorAlberto, "Aula virtual 5", "aula5necochea");
		
		plataforma.agregarPersona(estudiante);
		plataforma.agregarPersona(profesorAlberto);
		plataforma.agregarCurso(cursoFrances);
		
		plataforma.agregarInscripcion(estudiante, cursoFrances);
		plataforma.agregarInscripcion(estudiante, cursoFrances);
	}
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosPuedoBuscarUnCursoPorSuID() throws PersonaRepetidaException, CursoRepetidaException {
		Profesor profesorGiovanni = new Profesor(24201430L, "Giovanni", "Ferrari", "gioferrari@hotmail.com", "01122380143", 101286L);
		Curso cursoItaliano = new CursoPresencial(1L, "Curso Italiano A1", 60000.0, 20, Idioma.ITALIANO, 6 ,profesorGiovanni, "Aula 3");
		
		plataforma.agregarPersona(profesorGiovanni);
		plataforma.agregarCurso(cursoItaliano);
		Curso buscarCursoDeItaliano = plataforma.buscarCursoPorId(1L);
		assertEquals(cursoItaliano.getId(), buscarCursoDeItaliano.getId());
	}
	
	@Test(expected = CapacidadMaximaExcedidaException.class)
	public void dadoQueTengoUnaPlataformaDeCursosNoPuedoInscribirAUnEstudianteSiElCursoEstaLleno() throws PersonaRepetidaException, CursoRepetidaException, EstudianteDuplicadoException, CapacidadMaximaExcedidaException {
		Estudiante estudiante1 = new Estudiante(45394519L, "Lucas", "Maggio", "lukitasmaggio@hotmail.com", "01126520394", 2);
		Estudiante estudiante2 = new Estudiante(41890420L, "Franco", "Suarez", "francosuarez@hotmail.com", "01134521294", 5);
		Estudiante estudiante3 = new Estudiante(44394512L, "Agustin", "Klaus", "klausagus@outlook.com", "01131523174", 3);
		Estudiante estudiante4 = new Estudiante(45194512L, "Pablo", "Arlt", "pabloarlt@gmail.com", "01126520394", 7);
		Profesor profesorLee = new Profesor(27154819L, "Lee", "Xi", "leekahnxi@gmail.com", "01154610221", 172280L);
		Curso cursoChino = new CursoOnline(1L, "Curso Chino inicial", 50000.0, 3, Idioma.CHINO, 4, profesorLee, "Aula virtual 7", "aula7mandarin");
		
		plataforma.agregarPersona(estudiante1);
		plataforma.agregarPersona(estudiante2);
		plataforma.agregarPersona(estudiante3);
		plataforma.agregarPersona(estudiante4);
		plataforma.agregarCurso(cursoChino);
		
		plataforma.agregarInscripcion(estudiante1, cursoChino);
		plataforma.agregarInscripcion(estudiante2, cursoChino);
		plataforma.agregarInscripcion(estudiante3, cursoChino);
		plataforma.agregarInscripcion(estudiante4, cursoChino);
	}
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosPuedoVerificarLaCantidadDeEstudiantesInscriptosEnUnCurso() throws PersonaRepetidaException, CursoRepetidaException, EstudianteDuplicadoException, CapacidadMaximaExcedidaException {
		Estudiante estudiante1 = new Estudiante(45394519L, "Facundo", "Martinez", "facumartinez@hotmail.com", "01126520394", 2);
		Estudiante estudiante2 = new Estudiante(41890420L, "Sergio", "Dabove", "sdabove@hotmail.com", "01134521294", 5);
		Estudiante estudiante3 = new Estudiante(44394512L, "Nicolas", "Kramer", "kramernicolas@outlook.com", "01131523174", 3);
		Estudiante estudiante4 = new Estudiante(45194512L, "Benja", "Casas", "benjaacasas@gmail.com", "01126520394", 7);
		Profesor profesorAngel = new Profesor(27314426L, "Angel", "Dubois", "angeldubois@gmail.com", "01154610221", 208342L);
		Curso cursoIngles = new CursoPresencial(1L, "Curso Ingles B2", 60000.0, 10, Idioma.INGLES, 3, profesorAngel, "Aula 14");
		
		plataforma.agregarPersona(estudiante1);
		plataforma.agregarPersona(estudiante2);
		plataforma.agregarPersona(estudiante3);
		plataforma.agregarPersona(estudiante4);
		plataforma.agregarCurso(cursoIngles);
		
		plataforma.agregarInscripcion(estudiante1, cursoIngles);
		plataforma.agregarInscripcion(estudiante2, cursoIngles);
		plataforma.agregarInscripcion(estudiante3, cursoIngles);
		plataforma.agregarInscripcion(estudiante4, cursoIngles);
		
		Set<Estudiante> obtenerEstudiantes = plataforma.obtenerEstudiantesEnElCurso();
		assertEquals(4, obtenerEstudiantes.size());
	}
	
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosUnEstudiantePuedeEstarInscriptoEnDiferentesCursosALavez() throws PersonaRepetidaException, EstudianteDuplicadoException, CapacidadMaximaExcedidaException, CursoRepetidaException {
		Estudiante estudiante = new Estudiante(45194512L, "Marcos", "Furlan", "marcosnfurlan@gmail.com", "01126520394", 2);
		
		
		Profesor profesorAngel = new Profesor(27314426L, "Angel", "Dubois", "angeldubois@gmail.com", "01154610221", 208342L);
		Profesor profesorRoberto = new Profesor(30982401L, "Roberto", "Akinfeev", "lanutirober@hotmail.com", "01132016549", 17829L);
		Profesor profesorAlberto = new Profesor(28257216L, "Alberto", "Schneider", "schneideralbert@gmail.com", "01124384221", 142482L);
		
		Curso cursoFrances = new CursoPresencial(1L, "Curso Frances Inicial", 50000.0, 12, Idioma.FRANCES, 4, profesorAngel, "Aula 11");
		Curso cursoRuso = new CursoOnline(2L, "Curso Ruso", 42000.0, 15, Idioma.RUSO, 3, profesorRoberto, "Aula virtual 3", "aulamoscu");
		Curso cursoAleman = new CursoPresencial(3L, "Curso Aleman Avanzado", 65000.0, 16, Idioma.ALEMAN, 6, profesorAlberto, "Aula 12");
		
		plataforma.agregarPersona(estudiante);
		plataforma.agregarCurso(cursoFrances);
		plataforma.agregarCurso(cursoRuso);
		plataforma.agregarCurso(cursoAleman);
		plataforma.agregarPersona(profesorAngel);
		plataforma.agregarPersona(profesorRoberto);
		plataforma.agregarPersona(profesorAlberto);
		
		plataforma.agregarInscripcion(estudiante, cursoFrances);
		plataforma.agregarInscripcion(estudiante, cursoRuso);
		plataforma.agregarInscripcion(estudiante, cursoAleman);
		
		Set<Curso> obtenerCantidadCursosEstudiante = plataforma.obtenerCantidadCursos(estudiante);
		assertEquals(3, obtenerCantidadCursosEstudiante.size());
	}
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosPuedoObtenerLaCantidadDeCursosFiltradosPorIdioma() throws PersonaRepetidaException, CursoRepetidaException {
		Profesor profesorFranz = new Profesor(24531208L, "Franz", "Pertz", "franzpertz@outlook.com", "01125630194", 172301L);
		Profesor profesorClaudio = new Profesor(30231750L, "Claudio", "Schon", "schonclaudio@gmail.com", "01144307241", 203790L);
		Profesor profesorDavid = new Profesor(31761302L, "David", "Muller", "davidmuller@hotmail.com", "01162410254", 195032L);
		
		
		Curso cursoAleman1 = new CursoPresencial(1L, "Curso Aleman Avanzado", 65000.0, 16, Idioma.ALEMAN, 6, profesorFranz, "Aula 12");
		Curso cursoAleman2 = new CursoOnline(2L, "Curso Aleman Inicial", 40000.0, 20, Idioma.ALEMAN, 3, profesorClaudio, "Aula virtual 3", "aulaberlin3");
		Curso cursoAleman3 = new CursoPresencial(3L, "Curso Aleman Intermedio", 55000.0, 12, Idioma.ALEMAN, 6, profesorDavid, "Aula 15");
		
		plataforma.agregarPersona(profesorFranz);
		plataforma.agregarPersona(profesorClaudio);
		plataforma.agregarPersona(profesorDavid);
		
		plataforma.agregarCurso(cursoAleman1);
		plataforma.agregarCurso(cursoAleman2);
		plataforma.agregarCurso(cursoAleman3);
		
		Set<Curso> obtenerCursosPorIdioma = plataforma.obtenerCursosPorIdioma(Idioma.ALEMAN);
		Integer CantidadCursosEsperados = 3;
		
		assertEquals(CantidadCursosEsperados, obtenerCursosPorIdioma.size(),0.0);
	}
	
	@Test
	public void dadoQueTengoUnaPlataformaDeCursosPuedoObtenerElCursoMasLargo() throws PersonaRepetidaException, CursoRepetidaException {
		Profesor profesorJacques = new Profesor(23086312L, "Jacques", "Clemence", "jacquesclemence@gmail.com", "01145213094", 176540L);
		Profesor profesorXi = new Profesor(22459870L, "Xi", "Chen", "xichen@hotmail.com", "01142307540", 181094L);
		
		Curso cursoFrances = new CursoPresencial(1L, "Curso Frances Inicial", 50000.0, 12, Idioma.FRANCES, 4, profesorJacques, "Aula 11");
		Curso cursoChino = new CursoOnline(2L, "Curso Chino inicial", 50000.0, 10, Idioma.CHINO, 6, profesorXi, "Aula virtual 7", "aula7mandarin");
		
		plataforma.agregarPersona(profesorJacques);
		plataforma.agregarPersona(profesorXi);
		plataforma.agregarCurso(cursoFrances);
		plataforma.agregarCurso(cursoChino);
		
		Curso cursoMasLargo = plataforma.obtenerCursoMasLargo();
		
		assertEquals(cursoChino, cursoMasLargo);
		
	}
	
	
}
