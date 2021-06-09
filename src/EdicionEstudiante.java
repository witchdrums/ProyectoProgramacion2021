import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EdicionEstudiante extends JFrame {
Estudiante estudiante;
Administrador administrador;
Datos datos;
JList admin_EstudianteList;

JTextField nombre, matricula, fechaIngreso;
JButton guardar, cancelar, asignarMateria, removerMateria;
JList materiasAsignadas, materiasDisponibles;
JComboBox carreras, semestre, grupo;

GestionBoton gB = new GestionBoton();

public EdicionEstudiante(Administrador a, Estudiante e, Datos d, JList eL){
		super("Edición de estudiante: " + e.getNombre());
		this.estudiante = e;
		this.administrador = a;
		this.datos = d;
		this.admin_EstudianteList = eL;
	
		this.setBounds(500,200,500,380);
		this.setLayout(null);
		this.setResizable(false);
		this.nombre = new JTextField(estudiante.getNombre());
			this.nombre.setBounds(10,30,220,30);
			this.add(nombre);
			JLabel nombreLabel = new JLabel("Nombre del estudiante: ");
				nombreLabel.setBounds(10,10,200,20);
				this.add(nombreLabel);
		this.matricula = new JTextField(estudiante.getID());
			this.matricula.setBounds(260,30,80,30);
			this.add(matricula);
			JLabel matriculaLabel = new JLabel("Matrícula: ");
				matriculaLabel.setBounds(260,10,200,20);
				this.add(matriculaLabel);
		this.fechaIngreso = new JTextField(estudiante.getFechaIngreso());
			this.fechaIngreso.setEditable(false);
			this.fechaIngreso.setBounds(360,30,100,30);
			this.add(fechaIngreso);
			JLabel fechaLabel = new JLabel("Ingreso: ");
				fechaLabel.setBounds(360,10,200,20);
				this.add(fechaLabel);
			
		this.carreras = new JComboBox(datos.getCarreras());
			carreras.setBounds(10,85,220,30);
			this.add(carreras);
			carreras.setSelectedItem(estudiante.getCarreras());
			JLabel carrerasLabel = new JLabel("Carrera: ");
				carrerasLabel.setBounds(10,65,200,20);
				this.add(carrerasLabel);
		this.semestre = new JComboBox(Semestres);
			this.semestre.setBounds(260,85,80,30);
			this.semestre.setSelectedItem(String.valueOf(estudiante.getSemestre()));
			this.add(semestre);
			JLabel semestreLabel = new JLabel("Semestre: ");
				semestreLabel.setBounds(260,65,200,20);
				this.add(semestreLabel);
		this.grupo = new JComboBox(Grupo);
			this.grupo.setBounds(360,85,100,30);
			this.grupo.setSelectedItem(String.valueOf(estudiante.getGrupo()));
			this.add(grupo);
			JLabel grupoLabel = new JLabel("Grupo: ");
				grupoLabel.setBounds(360,65,200,20);
				this.add(grupoLabel);
		materiasAsignadas = new JList(estudiante.getMaterias());
		JScrollPane materiasAsignadasScroll = new JScrollPane(materiasAsignadas);
			materiasAsignadasScroll.setBounds(10,140,220,110);
			this.add(materiasAsignadasScroll);
			JLabel materiasAsignadasLabel = new JLabel("Materias asignadas: ");
				materiasAsignadasLabel.setBounds(10,120,200,20);
				this.add(materiasAsignadasLabel);
		removerMateria = new JButton("Remover >>");
			removerMateria.setBounds(10,260,220,30);
			this.add(removerMateria);
			removerMateria.addActionListener(gB);

		
		materiasDisponibles = new JList(datos.getMaterias());
		JScrollPane materiasDisponiblesScroll = new JScrollPane(materiasDisponibles);
			materiasDisponiblesScroll.setBounds(260,140,200,110);
			this.add(materiasDisponiblesScroll);
			JLabel materiasDisponiblesLabel = new JLabel("Materias disponibles: ");
				materiasDisponiblesLabel.setBounds(260,120,200,20);
				this.add(materiasDisponiblesLabel);
		asignarMateria = new JButton("<< Asignar");
			asignarMateria.setBounds(260,260,200,30);
			this.add(asignarMateria);
			asignarMateria.addActionListener(gB);
		
		guardar = new JButton("Guardar");
			guardar.setBounds(10,300,450,30);
			this.add(guardar);
			guardar.addActionListener(gB);
		
			
			
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	class GestionBoton implements ActionListener, Serializable{
		public void actionPerformed (ActionEvent e) {
			if (e.getSource() == removerMateria) {
				if (estudiante.getMaterias().size()>5) {
					((Materia)materiasAsignadas.getSelectedValue()).getCalificaciones().remove(estudiante);
					((Materia)materiasAsignadas.getSelectedValue()).getEstudiantes().remove(estudiante);
					estudiante.getMaterias().remove(materiasAsignadas.getSelectedValue());
					materiasAsignadas.setListData(estudiante.getMaterias());
					System.out.println("materias estudiante: " + estudiante.getMaterias().toString());
					//System.out.println("estudiantes en materia: " + estudiante.getMaterias().toString());
					estudiante.calcularPromedio();
				}else {
					JOptionPane.showMessageDialog(null, "El estudiante no puede tener menos de 5 materias");
				}
			}
			if (e.getSource() == asignarMateria) {
				for (Materia a : estudiante.getMaterias()) {
					if (materiasDisponibles.getSelectedValue().equals(a)) {
						System.out.println("repetida");
						return;
					}
				}
				((Materia)materiasDisponibles.getSelectedValue()).addEstudiante(estudiante);
				estudiante.getMaterias().add(((Materia)materiasDisponibles.getSelectedValue()));
				materiasAsignadas.setListData(estudiante.getMaterias());
				estudiante.calcularPromedio();
			}
			if (e.getSource() == guardar) {
				System.out.println("asdf");
				administrador.editarEstudiante(
						estudiante, 
						(Carrera)carreras.getSelectedItem(),
						nombre.getText(),
						matricula.getText(),
						semestre.getSelectedItem().toString(),
						grupo.getSelectedItem().toString());
			}
		}
	}
	String[] Semestres = {"Primero", "Segundo", "Tercero"};
	String[] Grupo = {"A", "B", "C"};
}