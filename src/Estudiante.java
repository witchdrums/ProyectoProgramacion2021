import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

public class Estudiante extends User implements Serializable{
private Carrera carrera;
private Vector<Materia>materias;
private Vector<Profesor>profesores;
private String semestre;
private String grupo;
private double promedio;

	public Estudiante() {}
	public Estudiante (String n, Carrera c) {
		super(n);
		this.carrera = c;
		c.getEstudiantes().add(this);
		this.materias = new Vector<Materia>();
	}
	public Estudiante (String n, Carrera c, Vector<Materia>m, String s, String g) {
		super(n);
		this.carrera = c;
		c.getEstudiantes().add(this);
		this.materias = m;
		Random rand = new Random();
		for (int i = 0; i<materias.size(); i++) {
			materias.get(i).addEstudiante(this);
			/*
			Double prom = 0.0;
			prom += rand.nextInt(9) + rand.nextDouble() + 4;
			if (prom>10) prom -= 2;
			materias.get(i).getCalificaciones().replace(this, prom);
			*/
			System.out.println(materias.get(i).getEstudiantes().toString());
		}
		calcularPromedio();
		this.semestre = s;
		this.grupo = g;
	}
	
	public void setMaterias(Vector<Materia>m) {this.materias = m;}
	public Vector<Materia> getMaterias(){return this.materias;}
	
	public void setProfesores(Vector<Profesor>p) { this.profesores = p; }
	public Vector<Profesor> getProfesores(){ return this.profesores; }
	
	public Carrera getCarreras(){ return this.carrera; }
	public void setCarrera(Carrera c) { 
		this.carrera = c; 
		carrera.getEstudiantes().add(this);
	}
	
	public String getSemestre() {return this.semestre; }
	public void setSemestre(String s) {this.semestre = s; }
	
	public String getGrupo() {return this.grupo; }
	public void setGrupo(String g) { this.grupo = g; }
	
	public double getPromedio() {
		calcularPromedio();
		return promedio;
	}
	public void setPromedio(double p) {this.promedio = p;}
	
	public String toString() {
		String info = super.getID();
		info += " | " + super.getNombre() + " | " + this.carrera + " | " + this.semestre + " | " + this.grupo + " | Promedio: " + this.promedio;
		return info;
	}
	
	
	public void calcularPromedio() {
		promedio = 0.0;
		for (Materia m : materias)
			promedio += (Double) m.getCalificaciones().get(this);
		promedio /= materias.size();
	}
}
