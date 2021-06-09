import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EdicionProf2 extends JFrame implements Serializable{
JTextField nombreProf, numeroProf, fechaProf;	
JButton guardarCambios, cancelar, removerMateria, asignarMateria;
JList materiasAsignadas, materiasDisponibles, materiaList, admin_materiaList; //materiaList viene de PanelPrincipal
JLabel nombreProfLabel, numeroProfLabel;
Director director;
Profesor profe;

Vector<String> NombreMaterias = new Vector<String>();
Vector<String> Materias= new Vector<String>();
Vector<String> nombreMateriasSinRepetidos= new Vector<String>();
DefaultListModel modeloAsignadas;
DefaultListModel modeloDisponibles;

GestionBoton gB = new GestionBoton();

	public EdicionProf2(Director d, Profesor p, JList mL, JList a_mL) {
		super("Edición de profesor: " + p.getNombre());
		this.director = d;
		this.profe = p;
		this.materiaList = mL;
		this.admin_materiaList = a_mL;
		this.NombreMaterias = director.getDatos().getNombreMaterias();
		this.Materias = new Vector<String>();
		modeloAsignadas = new DefaultListModel();
		modeloDisponibles = new DefaultListModel();
		
		
		nombreProf = new JTextField();
			nombreProf.setBounds(10,30,250,30);
			nombreProf.setText(this.profe.getNombre());
			//nombreProf.setText(director.getDatos().getProfesores().get(index).getNombre());
			this.add(nombreProf);
			nombreProfLabel = new JLabel("Nombre del profesor");
				nombreProfLabel.setBounds(10,10,200,20);
				nombreProfLabel.setForeground(Color.gray);
				this.add(nombreProfLabel);
		numeroProf = new JTextField();
			numeroProf.setBounds(270,30,100,30);
			numeroProf.setText(this.profe.getID());
			//numeroProf.setText(director.getDatos().getProfesores().get(index).getID());
			this.add(numeroProf);
			numeroProfLabel = new JLabel("# de empleado");
				numeroProfLabel.setBounds(270,10,200,20);
				numeroProfLabel.setForeground(Color.gray);
				this.add(numeroProfLabel);
		fechaProf = new JTextField(profe.getFechaIngreso());
			fechaProf.setBounds(380,30,100,30);
			fechaProf.setEditable(false);
			this.add(fechaProf);
		
		materiasAsignadas = new JList(modeloAsignadas);
			materiasAsignadas.setListData(profe.getMaterias());
		JScrollPane materiasAsignadasScroll = new JScrollPane(materiasAsignadas);
			materiasAsignadasScroll.setBounds(10,80,250,80);
			this.add(materiasAsignadasScroll);
		removerMateria = new JButton("Remover >>");
			removerMateria.setBounds(10,170,250,30);
			this.add(removerMateria);
			removerMateria.addActionListener(gB);
		
		materiasDisponibles = new JList(modeloDisponibles);
			materiasDisponibles.setListData(director.getDatos().getNombreMaterias());
			JScrollPane materiasDisponiblesScroll = new JScrollPane(materiasDisponibles);
				materiasDisponiblesScroll.setBounds(270,80,200,80);
				this.add(materiasDisponiblesScroll);
			asignarMateria = new JButton("<< Asignar");
				asignarMateria.setBounds(270,170,200,30);
				this.add(asignarMateria);
				asignarMateria.addActionListener(gB);
				
		guardarCambios = new JButton("Guardar cambios");
			guardarCambios.setBounds(10,210,460,30);
			this.add(guardarCambios);
			guardarCambios.addActionListener(gB);
				
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(500,200,500,300);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	class GestionBoton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == asignarMateria) {
				director.registrarMateria(((String)materiasDisponibles.getSelectedValue()), profe);
				materiasAsignadas.setListData(profe.getMaterias());
				admin_materiaList.setListData(director.getDatos().getMaterias());
				//System.out.println(profe.getMaterias().toString());
			}
			if (e.getSource() == removerMateria) {
				director.eliminarMateria(((Materia)materiasAsignadas.getSelectedValue()), profe);
				materiasAsignadas.setListData(profe.getMaterias());
				admin_materiaList.setListData(director.getDatos().getMaterias());
				//System.out.println(profe.getMaterias().toString());
			}
			if (e.getSource() == guardarCambios) {
				materiaList.setListData(director.getDatos().getMaterias());
				System.out.println(director.getDatos().getMaterias().toString());
				director.panelPrincipal.prof_ModeloMaterias.removeAllElements();
				director.modificarProfesor(profe, nombreProf.getText(), numeroProf.getText());
				
			}
		}
	}
}