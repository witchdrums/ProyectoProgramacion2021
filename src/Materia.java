import java.io.Serializable;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class Materia implements Serializable{
private String nombre;
private Profesor profesor;
private Hashtable calificaciones;
private Vector<Estudiante>estudiantes;
	public Materia() {}
	public Materia(String n, Profesor p) {
		this.nombre = n;
		this.profesor = p;
		p.getMaterias().add(this);
		estudiantes = new Vector<Estudiante>();
		//System.out.println("Materia creada: " + this.nombre);
		this.calificaciones = new Hashtable();
	}
    public void setNombre(String n) { this.nombre = n; }
    public String getNombre () {return this.nombre;}
    public void setProfesor(Profesor p) { this.profesor = p; }
    public Profesor getProfesor () {return this.profesor;}
	public void setEstudiantes(Vector<Estudiante>e) {this.estudiantes = e;}
	public Vector<Estudiante> getEstudiantes(){return this.estudiantes;}
	public Hashtable getCalificaciones() {return this.calificaciones; }
    public String toString() {
    	return this.nombre + " con " + this.profesor.getNombre();
    }
    public void addEstudiante(Estudiante e) {
    	Random rand = new Random();
    	estudiantes.add(e);
    	calificaciones.put(e, rand.nextInt(5) + rand.nextDouble() + 4);
    	System.out.print(calificaciones.keys());
    }
}
 