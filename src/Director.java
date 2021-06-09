import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JList;

public class Director extends User implements Serializacion, Serializable{
private Datos datos;
PanelPrincipal panelPrincipal;
	public Director() {}
	public Director(String n, Datos d, PanelPrincipal t) {
		super(n);
		this.datos = d;
		this.panelPrincipal = t;
	}
	public Datos getDatos() {return this.datos;}
	
	public Administrador registrarAdmin(String nombre, Carrera carrera) {
		Administrador nuevoAdmin = new Administrador(nombre, carrera, this);
		datos.getAdministradores().add(nuevoAdmin);
		this.panelPrincipal.adminList.setListData(datos.getAdministradores());
		return nuevoAdmin;
		
	}
	public void modificarAdmin(int index, String nombre, String numero, Carrera carrera) {
		datos.getAdministradores().get(index).setNombre(nombre);
		datos.getAdministradores().get(index).setID(numero);
		datos.getAdministradores().get(index).setCarrera(carrera);
		panelPrincipal.adminList.setModel(panelPrincipal.admin_SelectAdminModelo);
	}
	
	public void eliminarAdmin(Administrador a) {
		this.datos.getAdministradores().remove(a);
		System.out.println(datos.getAdministradores().toString());
		this.panelPrincipal.admin_SelectAdminModelo.removeElement(a);
		this.panelPrincipal.adminList.setModel(panelPrincipal.admin_SelectAdminModelo);
	}
	
	public void registrarMateria(String n, Profesor p) {
		datos.getMaterias().add(new Materia(n, p));
		panelPrincipal.admin_materiaList.setListData(datos.getMaterias());
	}
	
	public void eliminarMateria(Materia m, Profesor p) {
		Estudiante e = new Estudiante();
		datos.getMaterias().remove(m);
		for (int i = 0; i<p.getMaterias().size(); i++) {
			for (int j = 0; j<p.getMaterias().get(i).getEstudiantes().size(); j++) {
				p.getMaterias().get(i).getEstudiantes().get(j).getMaterias().remove(m);
				p.getMaterias().get(i).getEstudiantes().get(j).calcularPromedio();
			}
		}
		p.getMaterias().remove(m);
		this.panelPrincipal.materiaList.setListData(datos.getMaterias());
	}
	
	public Profesor registrarProfesor(String n) 
	{
		Profesor nuevoProfesor = new Profesor (n);
		datos.getProfesores().add(nuevoProfesor);
		this.panelPrincipal.admin_materiaList.setListData(datos.getMaterias());
		return nuevoProfesor;
	}
	
	public void modificarProfesor(Profesor p, String nombre, String ID) {
		p.setNombre(nombre);
		p.setID(ID);
	}
	public void eliminarProf(Profesor p) 
	{
		datos.getMaterias().removeAll(p.getMaterias());
		
		for(int i = 0; i<p.getMaterias().size(); i++) {
			for (int j = 0; j<p.getMaterias().get(i).getEstudiantes().size(); j++) {
				p.getMaterias().get(i).getEstudiantes().get(j).getMaterias().removeAll(p.getMaterias());	
			}
		}
			
		for (int i = 0; i<datos.getProfesores().size(); i++) {
			if (p.equals(datos.getProfesores().get(i))) {
				datos.getProfesores().remove(i);
			}
		}
		panelPrincipal.profList.setListData(datos.getProfesores());
		panelPrincipal.SelectProfesorModel.removeAllElements();
		panelPrincipal.SelectProfesorModel.addAll(datos.getProfesores());
		System.out.println(datos.getProfesores().toString());
	}
	
	public void exportar() {
		try {
			ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream("Personal.prsn"));
			objectOutput.writeObject(datos);
			objectOutput.close();
			System.out.println("Datos exportados");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public void importar() {
		try {
			ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream("Personal.prsn"));
			datos = ((Datos)objectInput.readObject());
			System.out.println("Datos importados");
			actualizarDependencias();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void actualizarDependencias() {
		panelPrincipal.adminList.removeAll();
		panelPrincipal.adminList.setListData(datos.getAdministradores());
		
		panelPrincipal.SelectCarreraModel.removeAllElements();
		panelPrincipal.SelectCarreraModel.addAll(datos.getCarreras());
		//profList = new JList(datos.getProfesores());
		panelPrincipal.profList.setListData(datos.getProfesores());
		
		panelPrincipal.SelectProfesorModel.removeAllElements();
		panelPrincipal.SelectProfesorModel.addAll(datos.getProfesores());
		//materiaList = new JList(datos.getMaterias());
		panelPrincipal.materiaList.setListData(datos.getMaterias());
		//admin_SelectAdmin = new JComboBox(this.datos.getAdministradores());
		
		//panelPrincipal.admin_SelectAdminModelo.removeAllElements();
		panelPrincipal.admin_SelectAdminModelo.addAll(datos.getAdministradores());
		panelPrincipal.admin_materiaList.setListData(datos.getMaterias());
		//panelPrincipal.admin_EstudianteList.setListData(datos.getEstudiantes());
		panelPrincipal.estudiantePromedio.setListData(datos.getEstudiantes());
	}
}
