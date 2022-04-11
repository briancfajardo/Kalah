package presentation;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class KalahGUI extends JFrame implements ActionListener {
	private JMenuBar menu;
	private JMenu archivoM;
	private JMenuItem nuevo;
	private JMenuItem abrir;
	private JMenuItem salvar;
	private JMenuItem salir;
	private JFileChooser archivos;
	private File partida;


	public KalahGUI(){
		setTitle("Kalah");
		prepareElements();
		prepareActions();
		setVisible(true);
	}
	private void prepareElements(){
		setSize(960,540);
		prepareElementsMenu();
		prepareElementsBoard();

	}

	private void prepareElementsMenu(){
		menu = new JMenuBar();
		setJMenuBar(menu);
		archivoM = new JMenu("Archivo");
		menu.add(archivoM);
		nuevo = new JMenuItem("Nuevo");
		abrir = new JMenuItem("Abrir");
		salvar = new JMenuItem("Salvar");
		salir = new JMenuItem("Salir");

		nuevo.addActionListener(this);
		abrir.addActionListener(this);
		salvar.addActionListener(this);
		salir.addActionListener(this);

		archivoM.add(nuevo);
		archivoM.add(abrir);
		archivoM.add(salvar);
		archivoM.add(salir);
	}
	private void prepareElementsBoard(){

	}

	private void prepareActions() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}
	private void close(){
		String opciones[] = {"Cerrar", "Cancelar"};
		int elecccion = JOptionPane.showOptionDialog(this, "Confirme si desea salir", "Cerrar",
		0,0,null, opciones,this);
		if(elecccion == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	private void abrirArchivos(){
		archivos = new JFileChooser();
		archivos.showOpenDialog(this);
		partida = archivos.getSelectedFile();
        JOptionPane.showMessageDialog(this,"El elemento está en construcción","Anuncio",
                1,null);
	}

	private void salvarArchivos(){
		archivos = new JFileChooser();
		archivos.showSaveDialog(this);
        JOptionPane.showMessageDialog(this,"El elemento está en construcción","Anuncio",
                1,null);
	}
	private void refresh(){

	}

	public static void main(String[] arg){
		KalahGUI gui = new KalahGUI();
		gui.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == nuevo){

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


	}
}