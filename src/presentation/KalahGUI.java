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
	private int rows = 3;
	private int cols = 6;
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
		//prepareElementsBeginning();
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
	private void prepareElementsBeginning(){
		JLabel titulo = new JLabel("KALAH");
		Button juego = new Button("Nuevo Juego");
		Button continuar = new Button("Configurar juego");
		Button salir = new Button("Salir");
		JPanel panelBotones = new JPanel();
		JPanel panelSalir = new JPanel();

		juego.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		juego.setSize(30,30);


		continuar.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		continuar.setSize(30,30);


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
		panelSalir.add(salir);
		panelSalir.setOpaque(false);


		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Serif", Font.CENTER_BASELINE, 100));

		add(titulo);
		add(panelBotones);
		add(panelSalir);


	}
	private void prepareElementsBoard(){
		ImageIcon imagen = new ImageIcon(getClass().getResource("/presentation/bola.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/presentation/bola2.png"));
		setLayout(new GridLayout(rows,cols));
		int cont = 0;
		for(int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){

				JButton aux = new JButton();
				aux.setOpaque(true);
				aux.setIcon(new ImageIcon(imagen.getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
				aux.setContentAreaFilled(false);
				aux.setText(cont+"");
				aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
				aux.setForeground(new Color(255, 255, 255));
				aux.setHorizontalTextPosition(SwingConstants.CENTER);
				aux.setVerticalTextPosition(SwingConstants.BOTTOM);
				aux.setVerticalAlignment(SwingConstants.CENTER);
				aux.setHorizontalAlignment(SwingConstants.LEFT);
				aux.setRolloverIcon(new ImageIcon(imagen2.getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
				aux.setBorderPainted(false);
				add(aux);
				cont += 1;
			}

		}

	}

    private void refresh(){
	    prepareElementsBoard();
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
		try {
			partida = archivos.getSelectedFile();
			String nombre = partida.getName();
			JOptionPane.showMessageDialog(this,"El elemento está en construcción, se está abriendo un archivo\n" + nombre,"Abrir",
					1,null);
		}catch (Exception e){

		}

	}

	private void salvarArchivos(){
		archivos = new JFileChooser();
		archivos.showSaveDialog(this);
		String nombre = archivos.getSelectedFile()+"";
		if (!nombre.equals(null+"")){
			JOptionPane.showMessageDialog(this,"El elemento está en construcción, se está guardando un archivo\n" + nombre,"Guardar",
					1,null);
		}

	}


	public static void main(String[] arg){
		KalahGUI gui = new KalahGUI();
		gui.setResizable(false);
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

	//Camilin :) :( :o
}