import java.io.Serializable;
import java.util.Vector;

public class Datos implements Serializable{
private Vector<Administrador> administradores;
private Vector<Profesor> profesores;
private Vector<Estudiante> estudiantes;
private Vector<Carrera> carreras;
private Vector<Materia> materias;
private Vector<String> nombreMaterias;
	public Datos() {
		administradores = new Vector<Administrador>();
		profesores = new Vector<Profesor>();
		carreras = new Vector<Carrera>();
		materias = new Vector<Materia>();
		nombreMaterias = new Vector<String>();
		estudiantes = new Vector<Estudiante>();
		llenarNombreMaterias();
	}
	public Datos(Vector<Administrador>a, Vector<Profesor> p, Vector<Carrera> c, Vector<Estudiante> e, Vector<Materia> m) {
		administradores = a;
		profesores = p;
		carreras = c;
		estudiantes = e;
		materias = m;
	}
	public void setAdministradores(Vector<Administrador>a) { this.administradores = a; }
	public Vector<Administrador> getAdministradores(){ return this.administradores; }
	public void setProfesores(Vector<Profesor>p) { this.profesores = p; }
	public Vector<Profesor> getProfesores(){ return this.profesores; }
	public void setEstudiantes(Vector<Estudiante>e) {this.estudiantes = e;}
	public Vector<Estudiante> getEstudiantes(){return this.estudiantes;}
	
	public void setCarreras(Vector<Carrera>c) { this.carreras = c; }
	public Vector<Carrera> getCarreras(){ return this.carreras; }
	public void setMaterias(Vector<Materia>c) { this.materias = c; }
	public Vector<Materia> getMaterias(){ return this.materias; }
	public void setNombreMaterias(Vector<String> m) {this.nombreMaterias = m;}
	public Vector<String> getNombreMaterias() {return this.nombreMaterias;}
	public void llenarNombreMaterias() {
		nombreMaterias.add("Programacion");
		nombreMaterias.add("Matematicas Discretas");
		nombreMaterias.add("Probabilidad");
		nombreMaterias.add("Comunicacion");
		nombreMaterias.add("Ingles");
		nombreMaterias.add("Algebra Lineal");
		nombreMaterias.add("Bases de Datos");
		nombreMaterias.add("Diseño de Software");
	}
}

