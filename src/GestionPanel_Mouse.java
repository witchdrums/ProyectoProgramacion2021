import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JList;

public class GestionPanel_Mouse implements MouseListener, Serializable{
private PanelPrincipal panel;
	public GestionPanel_Mouse(PanelPrincipal p){
		this.panel = p;
	}
	public void mouseClicked(MouseEvent e) {
		JList list = (JList)e.getSource();
		if (e.getClickCount() == 2 && e.getSource() == panel.adminList) {
			int index = list.locationToIndex(e.getPoint());
			//System.out.println(index);
			//System.out.println(panel.director.getDatos().getAdministradores().size());
			System.out.println(panel.director.getDatos().getAdministradores().get(index).toString());
			EdicionAdmin eA = new EdicionAdmin(index, panel.director);
		}
		if (e.getClickCount() == 2 && e.getSource() == panel.profList) {
			int index = list.locationToIndex(e.getPoint());
			System.out.println("asdf");
			EdicionProf2 eP = new EdicionProf2(panel.director, ((Profesor)panel.profList.getSelectedValue()), panel.materiaList, panel.admin_materiaList);
		}
		if (e.getClickCount() == 2 && e.getSource() == panel.admin_EstudianteList) {
			((Administrador)panel.admin_SelectAdmin.getSelectedItem()).edicionEstudiante(
					((Administrador)panel.admin_SelectAdmin.getSelectedItem()),
					((Estudiante)panel.admin_EstudianteList.getSelectedValue()),
					panel.admin_EstudianteList
					);
			System.out.println(((Estudiante)panel.admin_EstudianteList.getSelectedValue()).getGrupo());
		}
		if (e.getClickCount() == 1 && e.getSource() == panel.prof_EstudianteList) {
			Estudiante estudiante = (Estudiante) panel.prof_EstudianteList.getSelectedValue();
			Materia materia = (Materia) panel.prof_SelectMateria.getSelectedItem();
			panel.prof_califLabel.setText(materia.getCalificaciones().get(estudiante).toString());
		
		}
		if (e.getClickCount() == 2 && e.getSource() == panel.admin_materiaList) {
			System.out.println(((Materia)panel.admin_materiaList.getSelectedValue()).getEstudiantes().toString());
		}
		if (e.getClickCount() == 1 && e.getSource() == panel.estudiantePromedio) {
			panel.estudiantePromedioField.setText(Double.toString(  ((Estudiante)panel.estudiantePromedio.getSelectedValue()).getPromedio() ) );
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
