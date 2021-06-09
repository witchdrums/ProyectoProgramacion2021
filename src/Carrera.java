import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class Carrera implements Serializable{
String nombre;
String ID;
Vector<Administrador>administradores;
Vector<Estudiante>estudiantes;
	public Carrera() {}
	public Carrera(String n) {
		this.nombre = n;
		administradores = new Vector<Administrador>();
		estudiantes = new Vector<Estudiante>();
		this.ID = makeID();
	}
	public Carrera(String n, Vector<Administrador>a) {
		this.nombre = n;
		this.administradores = a;
		this.ID = makeID();
	}
	public void setNombre(String n) {this.nombre = n;}
	public String getNombre() {return this.nombre;}
	public void setID(String n) {this.ID = n;}
	public String getID() {return this.ID;}
	public void setAdministradores(Vector<Administrador>a) { this.administradores = a; }
	public Vector<Administrador> getAdministradores(){ return this.administradores; }
	public void setEstudiantes(Vector<Estudiante>a) { this.estudiantes = a; }
	public Vector<Estudiante> getEstudiantes(){ return this.estudiantes; }
	public String toString() {return this.nombre + " (" + this.ID + ")";}
    String makeID(){
    	Scanner scan = new Scanner(this.nombre);
    	scan.tokens();
    	String ID = "";
    	while(scan.hasNext()) {
    		char[] a = scan.next().toCharArray();
    		ID += a[0];
    	}
        return ID;
    }
}
//concatenar administrador con ID