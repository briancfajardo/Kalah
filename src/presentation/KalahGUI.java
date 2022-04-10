package presentation;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KalahGUI extends JFrame implements ActionListener {
	private JMenuBar menu;
	private JMenu archivo;
	private JMenuItem nuevo;
	private JMenuItem abrir;
	private JMenuItem salvar;
	private JMenuItem salir;


	public KalahGUI(){
		setTitle("Kalah");
		prepareElements();
		prepareActions();
		setVisible(true);
	}
	private void prepareElements(){
		setSize(960,540);
		prepareElementsMenu();

	}
	private void prepareElementsMenu(){
		menu = new JMenuBar();
		setJMenuBar(menu);
		archivo = new JMenu("Archivo");
		menu.add(archivo);
		nuevo = new JMenuItem("Nuevo");
		abrir = new JMenuItem("Abrir");
		salvar = new JMenuItem("Salvar");
		salir = new JMenuItem("Salir");

		nuevo.addActionListener(this);
		abrir.addActionListener(this);
		salvar.addActionListener(this);
		salir.addActionListener(this);

		archivo.add(nuevo);
		archivo.add(abrir);
		archivo.add(salvar);
		archivo.add(salir);

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

	public static void main(String[] arg){
		KalahGUI gui = new KalahGUI();
		gui.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == salir){
			close();
		}

	}
}