import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EdicionAdmin extends JFrame{
JTextField nombreAdmin, numeroAdmin;
JButton guardarCambios, cancelar;
JComboBox carrerasAdmin;
JLabel nombreAdminLabel, carrerasAdminLabel, numeroAdminLabel;
Director director;
int index;
GestionBoton gB = new GestionBoton();
	public EdicionAdmin(int i, Director d) {
		super("Edición de administrador: " + d.getDatos().getAdministradores().get(i).getNombre());
		this.director = d;
		this.index = i;
		
		this.setBounds(500,200,500,170);
		this.setLayout(null);
		
		nombreAdmin = new JTextField();
			nombreAdmin.setBounds(10,30,250,30);
			nombreAdmin.setText(director.getDatos().getAdministradores().get(index).getNombre());
			this.add(nombreAdmin);
			nombreAdminLabel = new JLabel("Nombre del administrador");
				nombreAdminLabel.setBounds(10,10,200,20);
				nombreAdminLabel.setForeground(Color.gray);
				this.add(nombreAdminLabel);
		numeroAdmin = new JTextField();
			numeroAdmin.setBounds(270,30,100,30);
			numeroAdmin.setText(director.getDatos().getAdministradores().get(index).getID());
			this.add(numeroAdmin);
			numeroAdminLabel = new JLabel("# de empleado");
				numeroAdminLabel.setBounds(270,10,200,20);
				numeroAdminLabel.setForeground(Color.gray);
				this.add(numeroAdminLabel);
		JTextField fechaIngreso = new JTextField();
			fechaIngreso.setEditable(false);
			fechaIngreso.setText(director.getDatos().getAdministradores().get(index).getFechaIngreso());
			fechaIngreso.setBounds(380,30,100,30);
			this.add(fechaIngreso);
		carrerasAdmin = new JComboBox(director.getDatos().getCarreras());
			carrerasAdmin.setBounds(10,80,250,30);
			this.add(carrerasAdmin);
			carrerasAdminLabel = new JLabel("Carrera");
				carrerasAdminLabel.setBounds(10,60,200,20);
				carrerasAdminLabel.setForeground(Color.gray);
				this.add(carrerasAdminLabel);
		guardarCambios = new JButton("Guardar cambios");
			guardarCambios.setBounds(270,80,210,30);
			this.add(guardarCambios);
			guardarCambios.addActionListener(gB);
		cancelar = new JButton("Cancelar");
			cancelar.setBounds(270,240,200,30);
			//this.add(cancelar);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	class GestionBoton implements ActionListener, Serializable{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == guardarCambios) {
				System.out.println("Guardar");
				director.modificarAdmin(index, nombreAdmin.getText(), numeroAdmin.getText(), ((Carrera)carrerasAdmin.getSelectedItem()));
			}
		}
	}
	
}
