package presentation;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KalahGUI extends JFrame {
	JMenu menu;
	public KalahGUI(){

		setVisible(true);
		setTitle("Kalah");
		prepareElements();
		prepareActions();
		menu = new JMenu("Nuevo menú");
	}
	public void prepareElements(){
		setSize(960,540);
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
	}
}