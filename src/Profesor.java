import java.io.Serializable;
import java.util.Vector;

public class Profesor extends User implements Serializable{
public Vector<Materia>materias;
	public Profesor() {}
	public Profesor(String n) {
		super(n);
		materias = new Vector<Materia>();
	}
	public void setMaterias(Vector<Materia>m) {this.materias = m;}
	public Vector<Materia> getMaterias(){return this.materias;}
	
	public void cambiarCalificacion(Estudiante estudiante, Materia materia, Double nuevaCalif) {
		if(materias.contains(materia))
			materia.getCalificaciones().replace(estudiante, nuevaCalif);
			estudiante.getPromedio();
	}
}
 