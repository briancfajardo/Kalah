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
		this.setContentPane(fondo);
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
		Button juego = new Button("Nuevo Juego");
		juego.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		juego.setSize(30,30);

		Button continuar = new Button("Continuar juego");
		continuar.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		continuar.setSize(30,30);

		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new CompoundBorder(new EmptyBorder(100,150,30,150),
				new TitledBorder("")));
		panelBotones.setLayout(new GridLayout(1,2));
		panelBotones.add(juego);
		panelBotones.add(continuar);

		setLayout(new FlowLayout());

		JPanel panelSalir = new JPanel();
		panelSalir.setBorder(new CompoundBorder(new EmptyBorder(30,150,30,150),
				new TitledBorder("")));
		panelSalir.setLayout(new GridLayout(1,1));

		Button salir = new Button("Salir");
		salir.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		salir.setSize(30,30);

		panelSalir.add(salir);
		//salir.setLocation(465,300);
		//salir.setBounds(465,300,100,30);
		//setLayout(border);
        JLabel titulo = new JLabel("KALAH");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Serif", Font.CENTER_BASELINE, 100));
		//titulo.setBackground(new Color(48, 145, 108));
		////titulo.setBounds(new Rectangle());
		////titulo.setVerticalAlignment(SwingConstants.CENTER);
		//titulo.setHorizontalAlignment(SwingConstants.LEADING);
		//add(titulo, BorderLayout.NORTH);
		//add(juego,BorderLayout.EAST);
		//add(continuar,BorderLayout.WEST);
		//add(salir,BorderLayout.SOUTH);
		add(titulo);
		add(panelBotones);
		add(panelSalir);

		//this.getContentPane().setBackground(new Color(128,64,0));


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
        JOptionPane.showMessageDialog(this,"El elemento está en construcción\n" + nombre,"Abrir",
                1,null);
	}

	private void salvarArchivos(){
		archivos = new JFileChooser();
		archivos.showSaveDialog(this);
		String nombre = archivos.getSelectedFile()+"";
        JOptionPane.showMessageDialog(this,"El elemento está en construcción\n" + nombre,"Guardar",
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
			imagen = new ImageIcon(getClass().getResource("/presentation/fondo3.jpg")).getImage();
			g.drawImage(imagen,0,0,getWidth(),getHeight(),this);

			setOpaque(false);

			super.paint(g);
		}
	}

	//Camilin :)
}