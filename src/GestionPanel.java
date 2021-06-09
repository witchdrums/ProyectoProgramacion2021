import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JOptionPane;

public class GestionPanel implements ActionListener, Serializable{
private PanelPrincipal panel;
	public GestionPanel(PanelPrincipal pp) {
		this.panel = pp;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panel.registrarAdmin) {
			Administrador nuevoAdmin = panel.director.registrarAdmin(panel.nombreAdmin.getText(), ((Carrera)panel.adminCarrera.getSelectedItem()));
			panel.admin_SelectAdminModelo.addElement(nuevoAdmin);
		}
		if(e.getSource() == panel.editarAdmin) {
			EdicionAdmin eA = new EdicionAdmin(panel.adminList.getSelectedIndex(), panel.director);
		}
		if(e.getSource() == panel.eliminarAdmin) {
			panel.director.eliminarAdmin(((Administrador)panel.adminList.getSelectedValue()));
		}
		if(e.getSource() == panel.eliminarProf) {
			panel.director.eliminarProf(((Profesor)panel.profList.getSelectedValue()));
		}
		if (e.getSource() == panel.registrarProf) {
			Profesor nuevoProfesor = panel.director.registrarProfesor(panel.nombreProf.getText());
			for (Object a : panel.profeMateriaList.getSelectedValues()) {
				System.out.println(a.toString());
				panel.director.registrarMateria(a.toString(), panel.director.getDatos().getProfesores().lastElement());
			}
			panel.profList.setListData(panel.director.getDatos().getProfesores());
			panel.materiaList.setListData(panel.director.getDatos().getMaterias());
			panel.SelectProfesorModel.addElement(nuevoProfesor);
		}
		if (e.getSource() == panel.editarProf) {
			EdicionProf2 eP = new EdicionProf2(panel.director, ((Profesor)panel.profList.getSelectedValue()), panel.materiaList, panel.admin_materiaList);
		}
		if(e.getSource() == panel.admin_actualizarLista) {
			try {
				panel.admin_EstudianteList.setListData(((Administrador)panel.admin_SelectAdmin.getSelectedItem()).getCarrea().getEstudiantes());
			}catch(NullPointerException npe2) {
				JOptionPane.showMessageDialog(null, "Seleccione un administrador antes de actualizar");
			}
		}
		if(e.getSource() == panel.admin_registrarEstudiante) {
			Vector<Materia>materiasSeleccionadas = new Vector<Materia>();
			Object[] m = (Object[]) panel.admin_materiaList.getSelectedValues();
			for (int i = 0; i<m.length; i++) {
				materiasSeleccionadas.add((Materia)m[i]);
			}
			try {
				((Administrador)panel.admin_SelectAdmin.getSelectedItem()).registrarEstudiante(
						panel.admin_nombreEstudiante.getText(),
						((Carrera)panel.admin_carreras.getSelectedItem()), 
						materiasSeleccionadas,
						panel.admin_semestre.getSelectedItem().toString(),
						panel.admin_grupo.getSelectedItem().toString());
				panel.admin_EstudianteList.setListData(((Administrador)panel.admin_SelectAdmin.getSelectedItem()).getCarrea().getEstudiantes());
				panel.actualizarListaPromedios();
			}catch(MenosDeCincoMateriasException eSCME) {
				JOptionPane.showMessageDialog(null, eSCME.getMessage());
			}catch(NullPointerException npe) {
				JOptionPane.showMessageDialog(null, "Seleccione un administrador antes de registrar un alumno");
			}
		}
		if (e.getSource() == panel.admin_Test) {
			System.out.println(((Estudiante)panel.admin_EstudianteList.getSelectedValue()).getMaterias().toString());
		}
		if(e.getSource() == panel.admin_Eliminar) {
			((Administrador)panel.admin_SelectAdmin.getSelectedItem()).eliminarEstudiante((Estudiante)panel.admin_EstudianteList.getSelectedValue());
			panel.admin_EstudianteList.setListData(((Administrador)panel.admin_SelectAdmin.getSelectedItem()).getCarrea().getEstudiantes());
		}
		if(e.getSource() == panel.prof_SelectProf) {
			try {
				panel.prof_ModeloMaterias.removeAllElements();
				panel.prof_ModeloMaterias.addAll(((Profesor)panel.prof_SelectProf.getSelectedItem()).getMaterias());
			}catch(NullPointerException npe) {
				panel.prof_ModeloMaterias.addAll(panel.director.getDatos().getProfesores().lastElement().getMaterias());
			}
		}
		if(e.getSource() == panel.prof_ActualizarLista) {
			try {
				panel.prof_EstudianteList.setListData(((Materia)panel.prof_SelectMateria.getSelectedItem()).getEstudiantes());
			}catch(NullPointerException npe) {
				JOptionPane.showMessageDialog(null, "Seleccione una materia antes de actualizar");
			}
			panel.prof_califLabel.setText("");
		}
		/*
		if(e.getSource() == panel.prof_Test) {
			EstudianteTableTest ETT = new EstudianteTableTest();
			ETT.EstudianteTableModel.add(panel.datos.getEstudiantes().get(0));
			for (int i = 0; i<((Materia)panel.prof_SelectMateria.getSelectedItem()).getCalificaciones().size(); i++) {
				System.out.println(((Materia)panel.prof_SelectMateria.getSelectedItem()).getCalificaciones());
			}
		}
		*/
		if (e.getSource() == panel.prof_cambiarCalif) {
			Estudiante estudiante = (Estudiante) panel.prof_EstudianteList.getSelectedValue();
			Materia materia = (Materia) panel.prof_SelectMateria.getSelectedItem();
			double calificacionNueva = Double.parseDouble(panel.prof_califActual.getText());
			((Profesor)panel.prof_SelectProf.getSelectedItem()).cambiarCalificacion(estudiante, materia, calificacionNueva);
			panel.prof_califActual.setText("");
			panel.prof_califLabel.setText(Double.toString(calificacionNueva));
		}

		if (e.getSource() == panel.semestrePromedioBox) {
			panel.grupoPromedioList.setListData(panel.getEstudiantesXSemestre(panel.semestrePromedioBox.getSelectedItem().toString()) );
		}
		if (e.getSource() == panel.exportar) {
			panel.director.exportar();
			JOptionPane.showMessageDialog(null, "Datos exportados en archivo: Personal.prsn");
		}
		if(e.getSource()== panel.importar){
			JOptionPane.showMessageDialog(null, "Datos importados de archivo: Personal.prsn");
			panel.director.importar();
		}
		if(e.getSource() == panel.crearCarrera) {
			Carrera nuevaCarrera = new Carrera(panel.carreraNombre.getText());
			panel.director.getDatos().getCarreras().add(nuevaCarrera);
			panel.SelectCarreraModel.addElement(nuevaCarrera);
			System.out.println(panel.director.getDatos().getAdministradores().toString());
			JOptionPane.showMessageDialog(null, "Nueva carrera creada: " + nuevaCarrera.getNombre());
			panel.carreraNombre.setText("");
		}
		if(e.getSource() == panel.semestrePromedioBox) {
			panel.estudiantesEnSemestre = panel.getEstudiantesXSemestre(panel.semestrePromedioBox.getSelectedItem().toString());
			panel.grupoPromedioList.setListData(panel.estudiantesEnSemestre);
		}
		if(e.getSource() == panel.aplicarFiltro) {
			panel.grupoPromedioList.setListData(panel.filtroGrupal(
					panel.semestrePromedioBox.getSelectedItem().toString(),
					panel.grupoPromedioBox.getSelectedItem().toString()));
		}
		if(e.getSource() == panel.calcularPromedioGrupal) {
			double promedio = 0.0;
			for (Estudiante estudiante : panel.estudiantesEnSemestre) {
				promedio += estudiante.getPromedio();
			}
			panel.semestrePromedioText.setText(Double.toString(promedio/panel.estudiantesEnSemestre.size()));
		}
	}
}
