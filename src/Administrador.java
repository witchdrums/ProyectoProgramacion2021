//import EstudianteSinCincoMateriasException;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.JList;

public class Administrador extends User implements Serializable{
Carrera carrera;
Director director;
	public Administrador() {}
	public Administrador(String n, Carrera c, Director d) {
		super(n);
		this.carrera = c;
		carrera.getAdministradores().add(this);
		this.director = d;
	}
	public void setCarrera(Carrera c) { this.carrera = c;}
	public Carrera getCarrea() { return this.carrera; }
	public String toString() { return super.toString() + " | " + this.carrera.getID(); }
	
	public int registrarEstudiante (String n, Carrera c, Vector<Materia>m, String s, String g) throws MenosDeCincoMateriasException  
	{
		if (m.size()>4) {
			director.getDatos().getEstudiantes().add(new Estudiante(n,c,m,s,g));
			return 0;
		}else {
			throw new MenosDeCincoMateriasException(
					"El estudiante no puede tener menos de 5 materias");
		}
	}

	
	public void edicionEstudiante(Administrador a, Estudiante e,  JList eL) {
		EdicionEstudiante eE = new EdicionEstudiante(a,e,director.getDatos(), eL);
	}
	
	public void editarEstudiante(Estudiante estudiante, Carrera c, String n, String m, String s, String g) {
		estudiante.getCarreras().getEstudiantes().remove(estudiante);
		estudiante.setCarrera(c);
		estudiante.setNombre(n);
		estudiante.setID(m);
		estudiante.setSemestre(s);
		estudiante.setGrupo(g);
	}
	
	public void eliminarEstudiante(Estudiante e) {
		director.getDatos().getEstudiantes().remove(e);
		carrera.getEstudiantes().remove(e);
		
		for (Materia a : director.getDatos().getMaterias()) {
			if (a.getCalificaciones().contains(e)) {
				a.getCalificaciones().remove(e);
			}
			if (a.getEstudiantes().contains(e)) {
				a.getEstudiantes().removeElement(e);
			}
		}
	}
}
