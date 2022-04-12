package presentation;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class KalahGUI extends JFrame implements ActionListener {

	private int ancho = 960;
	private int alto = 540;
	private JMenuBar menu;
	private JMenu archivoM;
	private JMenuItem nuevo;
	private JMenuItem abrir;
	private JMenuItem salvar;
	private JMenuItem salir;
	private JFileChooser archivos;
	private File partida;
	private Fondo fondo = new Fondo();


	public KalahGUI(){
		//this.setContentPane(fondo);
		setTitle("Kalah");
		prepareElements();
		prepareActions();
		setVisible(true);
	}
	private void prepareElements(){
		setSize(ancho,alto);
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

		BorderLayout border = new BorderLayout();
		Button juego = new Button("Nuevo Juego");
		Button continuar = new Button("Continuar juego");
		Button salir = new Button("Salir");
		setLayout(border);
        JLabel titulo = new JLabel("KALAH");
		titulo.setFont(new Font("Serif", Font.ROMAN_BASELINE, 50));
		titulo.setBackground(new Color(48, 145, 108));
		//titulo.setBounds(new Rectangle());
		//titulo.setVerticalAlignment(SwingConstants.CENTER);
		titulo.setHorizontalAlignment(SwingConstants.LEADING);
		add(titulo, BorderLayout.NORTH);
		add(juego,BorderLayout.EAST);
		add(continuar,BorderLayout.WEST);
		add(salir,BorderLayout.SOUTH);
		this.getContentPane().setBackground(new Color(128,64,0));


	}
    private void refresh(){

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
		String nombre = partida.getName();
        JOptionPane.showMessageDialog(this,"El elemento está en construcción\n" + nombre,"Anuncio",
                1,null);
	}

	private void salvarArchivos(){
		archivos = new JFileChooser();
		archivos.showSaveDialog(this);
		String nombre = archivos.getSelectedFile()+"";
        JOptionPane.showMessageDialog(this,"El elemento está en construcción\n" + nombre,"Anuncio",
                1,null);
	}


	public static void main(String[] arg){
		KalahGUI gui = new KalahGUI();
		//gui.setResizable(false);
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
	class Fondo extends JPanel{
		private Image imagen;
		@Override
		public void paint(Graphics g){
			imagen = new ImageIcon(getClass().getResource("/presentation/Madera.jpg")).getImage();
			g.drawImage(imagen,0,0,getWidth(),getHeight(),this);

			setOpaque(false);

			super.paint(g);
		}
	}

	//Camilin :)
}