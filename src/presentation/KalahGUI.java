package presentation;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Clase de la primera ventana que aparece al ejecutar la aplicación,
 * permite abrir y guardar archivos, configurar el juego, comenzar un
 * juego nuevo y cerrar
 *
 * @author Camilo Fajardo, Andrea Duran
 * @version 22/04/2022
 */
public class KalahGUI extends JFrame implements ActionListener {

	private int ancho = 960;
	private int alto = 540;
	private JMenuBar menu;
	private JMenu archivoM;
    private JMenuItem config;
	private JMenuItem nuevo;
	private JMenuItem abrir;
	private JMenuItem salvar;
	private JMenuItem salir;
	private JFileChooser archivos;
	private File partida;
	private Fondo fondo = new Fondo();


	/**
	 * Constructor de la clase KalahGUI
	 */
	public KalahGUI(){
		this.setContentPane(fondo);
		setTitle("Kalah");
		prepareElements();
		prepareActions();
		setVisible(true);

	}

	/**
	 * Prepara los elementos de la ventana inicial
	 * Modifica el tamaño y añade el menú y los botones iniciales
	 */
	private void prepareElements(){
		setSize(ancho,alto);
		prepareElementsMenu();
		prepareElementsBeginning();
	}

	/**
	 * Prepara los elementos necesarios para el menú, los botones y los
	 * layouts correspondientes
	 *
	 * Crea un JMenuBar y sus respectivos JMenuItems
	 *
	 */
	private void prepareElementsMenu(){
		menu = new JMenuBar();
		setJMenuBar(menu);
		archivoM = new JMenu("Archivo");

		archivoM.setCursor(new Cursor(Cursor.HAND_CURSOR));

		menu.add(archivoM);

		config = new JMenuItem("Configuración");
		config.addActionListener(this);
		config.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menu.add(config);

		nuevo = new JMenuItem("Nuevo");
		abrir = new JMenuItem("Abrir");
		salvar = new JMenuItem("Salvar");
		salir = new JMenuItem("Salir");

		nuevo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		abrir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		salvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR));

		nuevo.addActionListener(this);
		abrir.addActionListener(this);
		salvar.addActionListener(this);
		salir.addActionListener(this);

		archivoM.add(nuevo);
		archivoM.add(abrir);
		archivoM.add(salvar);
		archivoM.add(salir);

	}

	/**
	 * Prepara los elementos necesarios para los botones principales
	 * dentro de la pantalla de inicio, incluyendo el título y dos botones
	 *
	 * Le agrega a cada botón su propio oyente para que realiza el evento
	 * correspondiente
	 */
	private void prepareElementsBeginning(){
		JLabel titulo = new JLabel("KALAH");
		Button juego = new Button("Nuevo Juego");
		Button continuar = new Button("Configurar juego");
		Button salir = new Button("Salir");
		JPanel panelBotones = new JPanel();
		JPanel panelSalir = new JPanel();

		juego.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		juego.setSize(30,30);
		juego.setCursor(new Cursor(Cursor.HAND_CURSOR));
		juego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KalahGUIGame game = new KalahGUIGame();
				game.setVisible(true);
				game.setResizable(false);
				game.setLocationRelativeTo(null);
				dispose();
			}
		});

		continuar.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		continuar.setSize(30,30);
		continuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		continuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                KalahGUIConfig game = new KalahGUIConfig();
                game.setVisible(true);
                game.setResizable(false);
                game.setLocationRelativeTo(null);
                dispose();
			}
		});


		panelBotones.setBorder(new CompoundBorder(new EmptyBorder(100,1000,30,1000),
				new TitledBorder("")));
		panelBotones.setLayout(new GridLayout(1,2));
		panelBotones.add(juego);
		panelBotones.add(continuar);
		panelBotones.setOpaque(false);

		setLayout(new FlowLayout());

		panelSalir.setBorder(new CompoundBorder(new EmptyBorder(30,1000,30,1000),
				new TitledBorder("")));
		panelSalir.setLayout(new GridLayout(1,1));


		salir.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		salir.setSize(30,30);
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		panelSalir.add(salir);
		panelSalir.setOpaque(false);


		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Serif", Font.CENTER_BASELINE, 100));

		add(titulo);
		add(panelBotones);
		add(panelSalir);


	}

	/**
	 * Modifica la acción de cerrar para solicitar confirmación
	 */
	private void prepareActions() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}

	/**
	 * Método que genera una ventana emergente que solicita confirmación acerca de
	 * si se desea cerrar la ventana actual o no
	 */
	private void close(){
		String opciones[] = {"Cerrar", "Cancelar"};
		int elecccion = JOptionPane.showOptionDialog(this, "Confirme si desea salir", "Cerrar",
		0,0,null, opciones,this);
		if(elecccion == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}

	/**
	 * Evento que se realiza cuando se da clic en el botón de abrir
	 * Genera un JFileChooser
	 */
	private void abrirArchivos(){

		archivos = new JFileChooser();
		archivos.showOpenDialog(this);
		try {
			partida = archivos.getSelectedFile();
			String nombre = partida.getName();
			JOptionPane.showMessageDialog(this,"El elemento está en construcción, se está abriendo un archivo\n" + nombre,"Abrir",
					1,null);
		}catch (Exception e){

		}

	}

	/**
	 * Evento que se realiza cuando se da clic en el botón de guardar
	 * Genera un JFileChooser
	 */
	private void salvarArchivos(){
		archivos = new JFileChooser();
		archivos.showSaveDialog(this);
		String nombre = archivos.getSelectedFile()+"";
		if (!nombre.equals(null+"")){
			JOptionPane.showMessageDialog(this,"El elemento está en construcción, se está guardando un archivo\n" + nombre,"Guardar",
					1,null);
		}

	}

	/**
	 * Método princiapl que ejecuta la aplicación
	 * @param arg
	 */
	public static void main(String[] arg){
		KalahGUI gui = new KalahGUI();
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == nuevo){
			KalahGUIGame game = new KalahGUIGame();
			game.setVisible(true);
			game.setResizable(false);
			game.setLocationRelativeTo(null);
			dispose();
		}
		if(e.getSource() == abrir){
			abrirArchivos();
		}
		if(e.getSource() == salvar){
			salvarArchivos();
		}
		if(e.getSource() == salir){
			close();
		}
        if(e.getSource() == config){
            KalahGUIConfig game = new KalahGUIConfig();
            game.setVisible(true);
            game.setResizable(false);
            game.setLocationRelativeTo(null);
            dispose();
        }


	}

	/**
	 * Clase que genera un nuevo fondo para el JFrame de una imagen dentro del
	 * paquete presentation
	 */
	class Fondo extends JPanel{
		private Image imagen;
		@Override
		public void paint(Graphics g){
			imagen = new ImageIcon(getClass().getResource("/presentation/fondo3.jpg")).getImage();
			g.drawImage(imagen,0,0,getWidth(),getHeight(),this);

			setOpaque(false);

			super.paint(g);
		}
	}

	//Camilin :) :( :o
}