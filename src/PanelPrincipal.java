import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import static javax.swing.JOptionPane.showMessageDialog;

public class PanelPrincipal extends JFrame implements Serializable{
JTabbedPane tabbedPane;
JPanel panel;
JButton registrarAdmin, registrarProf, guardarLista, editarAdmin, eliminarAdmin, editarProf, eliminarProf, admin_actualizarLista, 
admin_registrarEstudiante, admin_Test, admin_Eliminar, prof_ActualizarLista, prof_Test, prof_cambiarCalif, exportar, importar,
crearCarrera, aplicarFiltro,calcularPromedioGrupal;
JComboBox adminCarrera, admin_SelectAdmin,admin_carreras, admin_semestre, admin_grupo, prof_SelectProf, prof_SelectMateria;
JComboBox grupoPromedioBox,semestrePromedioBox;
JTextField nombreAdmin, nombreProf,admin_nombreEstudiante, prof_nuevaCalif, prof_califNueva,carreraNombre,
estudiantePromedioField,semestrePromedioText;
JList adminList, profList, profeMateriaList, materiaList, admin_EstudianteList,admin_materiaList,prof_EstudianteList,
estudiantePromedio,grupoPromedioList,semestrePromedioList;
JLabel estudiantePromedioLabel;
JLabel prof_califLabel;
JTextField prof_califActual;
Director director;

Vector<Estudiante> estudiantesEnSemestre = new Vector<Estudiante>();
Vector<Estudiante> estudiantesEnGrupo = new Vector<Estudiante>();

DefaultComboBoxModel<Materia>prof_ModeloMaterias;
DefaultComboBoxModel<Administrador>admin_SelectAdminModelo;
DefaultComboBoxModel<Carrera>SelectCarreraModel = new DefaultComboBoxModel<Carrera>();
DefaultComboBoxModel<Profesor>SelectProfesorModel = new DefaultComboBoxModel<Profesor>();

GestionPanel gB;
GestionPanel_Mouse gM;

static Datos datos;
	public PanelPrincipal() {
		super("Gestión de Personal");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        this.setResizable(false);
		gB = new GestionPanel(this);
		gM = new GestionPanel_Mouse(this);
		datos = new Datos();
		director = new Director("Alejandro Chacon Fernandez", datos, this);
		this.setBounds(500,200,370,500);
		
		tabbedPane = new JTabbedPane();
			tabbedPane.setBounds(0,0,500,500);
			crearPanelDirector();
			crearPanelAdministrador();
			crearPanelProfesor();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(tabbedPane);	
		this.setVisible(true);
	}
	
	public void actualizarListaPromedios() {
		estudiantePromedio.setListData(director.getDatos().getEstudiantes());
	}
	
	public Vector<Estudiante> filtroGrupal(String semestre, String grupo){
		estudiantesEnSemestre.clear();
		for (int i = 0; i<director.getDatos().getEstudiantes().size(); i++) {
			if (director.getDatos().getEstudiantes().get(i).getSemestre().equals(semestre) &&
				director.getDatos().getEstudiantes().get(i).getGrupo().equals(grupo)) {
				estudiantesEnSemestre.add(director.getDatos().getEstudiantes().get(i));
			}
		}
		return estudiantesEnSemestre;
	}

	public Vector<Estudiante> getEstudiantesXSemestre(String semestre){
		estudiantesEnSemestre.clear();
		for (int i = 0; i<director.getDatos().getEstudiantes().size(); i++) {
			if (director.getDatos().getEstudiantes().get(i).getSemestre().equals(semestre)) {
				estudiantesEnSemestre.add(director.getDatos().getEstudiantes().get(i));
			}
		}
		return estudiantesEnSemestre;	
	}
	
	String[] Semestres = {"Primero", "Segundo", "Tercero"};
	String[] Grupo = {"A", "B", "C"};
	
	public void crearPanelDirector(){
		JPanel dirPanel = new JPanel();
		dirPanel.setLayout(null);
		guardarLista = new JButton("Guardar lista");
			guardarLista.setBounds(getBounds());
		JTabbedPane listas = new JTabbedPane();
			JPanel adminListPanel = new JPanel();
				adminListPanel.setLayout(null);
				admin_SelectAdminModelo = new DefaultComboBoxModel();
				adminList = new JList(datos.getProfesores());
					adminList.addMouseListener(gM);
				JScrollPane adminListScroll = new JScrollPane(adminList);
					adminListScroll.setBounds(5,220,325,150);
					adminListPanel.add(adminListScroll);
					JLabel adminListScrollLabel = new JLabel("Administradores registrados: ");
					adminListScrollLabel.setBounds(5,200,325,20);
					adminListPanel.add(adminListScrollLabel);
				nombreAdmin = new JTextField();
					nombreAdmin.setBounds(5,23,325,30);
					adminListPanel.add(nombreAdmin);
					JLabel nombreAdminLabel = new JLabel("Nombre del nuevo administrador: ");
					nombreAdminLabel.setBounds(5,0,200,30);
					adminListPanel.add(nombreAdminLabel);
				adminCarrera = new JComboBox(SelectCarreraModel);
					adminCarrera.setBounds(5,70,325,30);
					adminListPanel.add(adminCarrera);
					JLabel adminCarreraLabel = new JLabel("Carrera del nuevo administrador: ");
					adminCarreraLabel.setBounds(5,47,200,30);
					adminListPanel.add(adminCarreraLabel);
				registrarAdmin = new JButton("Registrar");
					registrarAdmin.setBounds(5,105,325,30);
					adminListPanel.add(registrarAdmin);
					registrarAdmin.addActionListener(gB);	
				editarAdmin = new JButton("Editar");
					editarAdmin.setBounds(5,135,325,30);
					adminListPanel.add(editarAdmin);
					editarAdmin.addActionListener(gB);
				eliminarAdmin = new JButton("Eliminar");
					eliminarAdmin.setBounds(5,165,325,30);
					adminListPanel.add(eliminarAdmin);
					eliminarAdmin.addActionListener(gB);
					
			listas.addTab("Administradores",adminListPanel);
			
			JPanel profListPanel = new JPanel();
				profListPanel.setLayout(null);
				profList = new JList(datos.getProfesores());
					profList.addMouseListener(gM);
				JScrollPane profListScroll = new JScrollPane(profList);
					profListScroll.setBounds(5,190,325,180);
					profListPanel.add(profListScroll);
					JLabel profListLabel = new JLabel("Profesores registrados:"); 
					profListLabel.setBounds(5,167,325,30);
					profListPanel.add(profListLabel);
				nombreProf = new JTextField();
					nombreProf.setBounds(5,23,325,30);
					profListPanel.add(nombreProf);
					JLabel nombreProfLabel = new JLabel("Nombre del profesor:"); 
					nombreProfLabel.setBounds(5,0,325,30);
					profListPanel.add(nombreProfLabel);
				profeMateriaList = new JList(datos.getNombreMaterias());
				JScrollPane profeMateriaListScroll = new JScrollPane(profeMateriaList);
					profeMateriaListScroll.setBounds(5,70,325,70);
					profeMateriaList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					profListPanel.add(profeMateriaListScroll);
					JLabel materiaListLabel = new JLabel("Materias:"); 
					materiaListLabel.setBounds(5,47,325,30);
					profListPanel.add(materiaListLabel);
				registrarProf = new JButton("Registrar");
					registrarProf.setBounds(5,143,150,30);
					profListPanel.add(registrarProf);
					registrarProf.addActionListener(gB);
				editarProf = new JButton("Editar");
					editarProf.setBounds(170,143,70,30);
					editarProf.addActionListener(gB);
					profListPanel.add(editarProf);
				eliminarProf = new JButton("Eliminar");
					eliminarProf.setBounds(245,143,85,30);
					eliminarProf.addActionListener(gB);
					profListPanel.add(eliminarProf);
			listas.addTab("Profesores", profListPanel);
			
			JPanel materiaListPanel = new JPanel();
				materiaListPanel.setLayout(null);
				materiaList = new JList(datos.getMaterias());
				JScrollPane materiaListScroll = new JScrollPane(materiaList);
					materiaListScroll.setBounds(5,0,340,370);
				materiaListPanel.add(materiaListScroll);
			listas.addTab("Materias", materiaListPanel);
		
			JPanel promediosPanel = new JPanel();
				promediosPanel.setLayout(null);
					JTabbedPane tiposPromedio = new JTabbedPane();
						JPanel alumnoPromedio = new JPanel();
							alumnoPromedio.setLayout(null);
						tiposPromedio.addTab("Alumno", alumnoPromedio);
							estudiantePromedio = new JList(datos.getEstudiantes());
								estudiantePromedio.addMouseListener(gM);
							JScrollPane alumnoPromedioListScroll = new JScrollPane(estudiantePromedio);
								alumnoPromedioListScroll.setBounds(0,0,340,200);
								alumnoPromedio.add(alumnoPromedioListScroll);
							estudiantePromedioLabel = new JLabel("Promedio del alumno: ");
								estudiantePromedioLabel.setBounds(5,200,200,50);
								alumnoPromedio.add(estudiantePromedioLabel);
							estudiantePromedioField = new JTextField();
								estudiantePromedioField.setEditable(false);
								estudiantePromedioField.setBounds(5,230,200,30);
								alumnoPromedio.add(estudiantePromedioField);
						
						JPanel grupoPromedio = new JPanel();
							grupoPromedio.setLayout(null);
						tiposPromedio.addTab("Semestral", grupoPromedio);
							grupoPromedioBox = new JComboBox(Grupo);
								grupoPromedioBox.setBounds(175,220,60,30);
								grupoPromedio.add(grupoPromedioBox);
								grupoPromedioBox.addActionListener(gB);
								JLabel grupoPromedioBoxLabel = new JLabel("Filtro grupal:");
									grupoPromedioBoxLabel.setBounds(175,198,160,30);
									grupoPromedio.add(grupoPromedioBoxLabel);
								aplicarFiltro = new JButton("Aplicar Filtro");
									aplicarFiltro.setBounds(235,219,100,32);
									grupoPromedio.add(aplicarFiltro);
									aplicarFiltro.addActionListener(gB);
							semestrePromedioBox = new JComboBox(Semestres);
								semestrePromedioBox.setBounds(5,220,160,30);
								grupoPromedio.add(semestrePromedioBox);
								semestrePromedioBox.addActionListener(gB);
								JLabel semestrePromedioBoxLabel = new JLabel("Semestre:");
									semestrePromedioBoxLabel.setBounds(5,198,160,30);
									grupoPromedio.add(semestrePromedioBoxLabel);
							semestrePromedioText = new JTextField();
								semestrePromedioText.setBounds(88,300,160,30);
									grupoPromedio.add(semestrePromedioText);
							calcularPromedioGrupal = new JButton("Calcular Promedio");
								calcularPromedioGrupal.setBounds(88,270,160,30);
								grupoPromedio.add(calcularPromedioGrupal);
								calcularPromedioGrupal.addActionListener(gB);									
							grupoPromedioList = new JList();
							JScrollPane grupoPromedioListScroll = new JScrollPane(grupoPromedioList);
								grupoPromedioListScroll.setBounds(0,0,340,200);
								grupoPromedio.add(grupoPromedioListScroll);
								

						tiposPromedio.setBounds(0,0,400,400);
					promediosPanel.add(tiposPromedio);
				
			listas.addTab("Promedios", promediosPanel);
			JPanel carrerasPanel = new JPanel();
				carrerasPanel.setLayout(null);
			listas.addTab("Carreras", carrerasPanel);
				crearCarrera = new JButton("Registrar Carrera");
					crearCarrera.setBounds(5,70,325,30);
					carrerasPanel.add(crearCarrera);
					crearCarrera.addActionListener(gB);
				carreraNombre = new JTextField();
					carreraNombre.setBounds(5,23,325,30);
					carrerasPanel.add(carreraNombre);
					JLabel carerraNombreLabel = new JLabel("Nombre de la nueva carrera: ");
						carerraNombreLabel.setBounds(5,0,200,30);
						carrerasPanel.add(carerraNombreLabel);
			listas.setBounds(0,0,500,400);
			
		exportar = new JButton("Exportar");
			exportar.setBounds(5,400,160,30);
			exportar.addActionListener(gB);
		importar = new JButton("Importar");
			importar.setBounds(172,400,160,30);
			importar.addActionListener(gB);
		dirPanel.add(importar);
		dirPanel.add(exportar);
		dirPanel.add(listas);
	tabbedPane.addTab("Director", dirPanel);
	}
	
	public void crearPanelAdministrador() {
		JPanel AdminPanel = new JPanel();
		
		AdminPanel.setLayout(null);
		admin_SelectAdmin = new JComboBox(admin_SelectAdminModelo);
			admin_SelectAdmin.setBounds(5,18,190,30);
			AdminPanel.add(admin_SelectAdmin);
			JLabel SelectAdminLabel = new JLabel("Seleccione administrador: ");
			SelectAdminLabel.setBounds(5,0,190,20);
			AdminPanel.add(SelectAdminLabel);
		admin_EstudianteList = new JList();
		JScrollPane admin_EstudianteListScroll = new JScrollPane(admin_EstudianteList);
			admin_EstudianteListScroll.setBounds(5,300,325,130);
			AdminPanel.add(admin_EstudianteListScroll);
			admin_EstudianteList.addMouseListener(gM);
			JLabel estudianteListLabel = new JLabel("Estudiantes registrados:");
				estudianteListLabel.setBounds(5,283,150,20);
				AdminPanel.add(estudianteListLabel);
		admin_actualizarLista = new JButton ("Actualizar lista");
			admin_actualizarLista.setBounds(210,18,120,30);
			AdminPanel.add(admin_actualizarLista);
			admin_actualizarLista.addActionListener(gB);
		admin_nombreEstudiante = new JTextField();
			admin_nombreEstudiante.setBounds(5,66,325,30);
			AdminPanel.add(admin_nombreEstudiante);
			JLabel admin_nombreEstudianteLabel = new JLabel("Nombre del nuevo estudiante: ");
				admin_nombreEstudianteLabel.setBounds(5,43,200,30);
				AdminPanel.add(admin_nombreEstudianteLabel);
		admin_carreras = new JComboBox(SelectCarreraModel);
			admin_carreras.setBounds(5,113,210,30);
			AdminPanel.add(admin_carreras);
			JLabel carreraLabel = new JLabel("Carrera del nuevo estudiante:");
				carreraLabel.setBounds(5,96,150,20);
				AdminPanel.add(carreraLabel);
		admin_semestre = new JComboBox(Semestres);
			admin_semestre.setBounds(220,113,65,30);
			AdminPanel.add(admin_semestre);
			JLabel semestreLabel = new JLabel("Semestre:");
				semestreLabel.setBounds(220,96,60,20);
				AdminPanel.add(semestreLabel);
		admin_grupo = new JComboBox(Grupo);
			admin_grupo.setBounds(290,113,40,30);
			AdminPanel.add(admin_grupo);
			JLabel grupoLabel = new JLabel("Grupo:");
				grupoLabel.setBounds(290,96,60,20);
				AdminPanel.add(grupoLabel);
		admin_materiaList = new JList(datos.getMaterias());
		JScrollPane admin_materiaListScroll = new JScrollPane(admin_materiaList);
			admin_materiaListScroll.setBounds(5,165,325,80);
			AdminPanel.add(admin_materiaListScroll);
			admin_materiaList.addMouseListener(gM);
			JLabel admin_materiaListScrollLabel = new JLabel("Materias disponibles: ");
				admin_materiaListScrollLabel.setBounds(5,147,150,20);
				AdminPanel.add(admin_materiaListScrollLabel);
		admin_registrarEstudiante = new JButton("Registrar");
			admin_registrarEstudiante.setBounds(5,250,150,30);
			AdminPanel.add(admin_registrarEstudiante);
			admin_registrarEstudiante.addActionListener(gB);
		admin_Test = new JButton("Test");
			admin_Test.setBounds(320,600,150,30);
			AdminPanel.add(admin_Test);
			admin_Test.addActionListener(gB);
		admin_Eliminar = new JButton("Eliminar");
			admin_Eliminar.setBounds(245,250,85,30);
			AdminPanel.add(admin_Eliminar);
			admin_Eliminar.addActionListener(gB);;
			
	tabbedPane.addTab("Administrador", AdminPanel);
	}

	public void crearPanelProfesor() {
		JPanel ProfPanel = new JPanel();
		ProfPanel.setLayout(null);
		
		SelectProfesorModel.addAll(director.getDatos().getProfesores());
		prof_SelectProf = new JComboBox(SelectProfesorModel);
			prof_SelectProf.setBounds(5,18,325,30);
			ProfPanel.add(prof_SelectProf);
			prof_SelectProf.addActionListener(gB);
			JLabel prof_SelectProfLabel = new JLabel("Seleccione profesor: ");
				prof_SelectProfLabel.setBounds(5,0,190,20);
				ProfPanel.add(prof_SelectProfLabel);
		prof_ModeloMaterias = new DefaultComboBoxModel();
		
		if (datos.getProfesores().size() != 0)
			prof_ModeloMaterias.addAll(((Profesor)prof_SelectProf.getSelectedItem()).getMaterias());
		
		prof_SelectMateria = new JComboBox(prof_ModeloMaterias);
			prof_SelectMateria.setBounds(5,66,325,30);
			ProfPanel.add(prof_SelectMateria);
			prof_SelectMateria.addActionListener(gB);
			JLabel prof_SelectMateriaLabel = new JLabel("Seleccione materia: ");
				prof_SelectMateriaLabel.setBounds(5,48,190,20);
				ProfPanel.add(prof_SelectMateriaLabel);
		prof_ActualizarLista = new JButton ("Actualizar lista");
			prof_ActualizarLista.setBounds(5,113,325,30);
			ProfPanel.add(prof_ActualizarLista);
			prof_ActualizarLista.addActionListener(gB);
			
		prof_EstudianteList = new JList();
		JScrollPane prof_EstudianteListScroll = new JScrollPane(prof_EstudianteList);
			prof_EstudianteListScroll.setBounds(5,165,325,200);
			ProfPanel.add(prof_EstudianteListScroll);
			prof_EstudianteList.addMouseListener(gM);
			JLabel prof_EstudianteListScrollLabel = new JLabel("Estudiantes registrados en materia: ");
				prof_EstudianteListScrollLabel.setBounds(5,147,250,20);
				ProfPanel.add(prof_EstudianteListScrollLabel);
			
		prof_califActual = new JTextField();
			prof_califActual.setBounds(200,370,130,30);
			ProfPanel.add(prof_califActual);
		prof_cambiarCalif = new JButton("Asignar calificacion");
			prof_cambiarCalif.setBounds(200,400,130,30);
			ProfPanel.add(prof_cambiarCalif);
			prof_cambiarCalif.addActionListener(gB);
		
		JLabel prof_calificacion = new JLabel("Calificacion: ");
			prof_calificacion.setBounds(5,355,130,50);
			prof_calificacion.setFont(new Font(null,Font.PLAIN, 15));
			ProfPanel.add(prof_calificacion);
		prof_califLabel = new JLabel("0");
			prof_califLabel.setBounds(5,385,130,50);
			prof_califLabel.setFont(new Font(null,Font.PLAIN, 50));
			
			ProfPanel.add(prof_califLabel);
		prof_Test = new JButton("Test");
			prof_Test.setBounds(320,600,150,30);
			ProfPanel.add(prof_Test);
			prof_Test.addActionListener(gB);
		
	
	tabbedPane.addTab("Profesor", ProfPanel);
	}
}
