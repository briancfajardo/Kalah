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

public class KalahGUIGame extends JFrame implements ActionListener {

    private int ancho = 960;
    private int alto = 540;
    private int rows = 3;
    private int cols = 6;
    private int seed = 3;
    private int valor1 = 0;
    private int valor2 = 0;
    private int mov1 = 0;
    private int mov2 = 0;
    private JMenuBar menu;
    private JMenu archivoM;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salir;
    private JFileChooser archivos;
    private File partida;
    private Fondo fondo = new Fondo();


    public KalahGUIGame(){
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
    //Carga de imagenes

    private void prepareElementsBoard(){
        ImageIcon imagen = new ImageIcon(getClass().getResource("/presentation/bola.png"));
        ImageIcon imagen3 = new ImageIcon(getClass().getResource("/presentation/transparente.png"));
        //ImageIcon semilla3 = new ImageIcon(getClass().getResource("/presentation/3semillas.png"));
        ImageIcon azules = new ImageIcon(getClass().getResource("/presentation/3semillasAzul.png"));
        ImageIcon zoomAzules = new ImageIcon(getClass().getResource("/presentation/zoom3semillasAzul.png"));
        ImageIcon rojas = new ImageIcon(getClass().getResource("/presentation/3semillasRoja.png"));
        ImageIcon zoomRojas = new ImageIcon(getClass().getResource("/presentation/zoom3semillasRoja.png"));

        setLayout(new GridLayout(rows,cols));
        int cont = 0;
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                if (i != 1) {
                    JButton aux = new JButton();
                    aux.setOpaque(true);
                    aux.setContentAreaFilled(false);
                    if (i == 0) {
                        aux.setIcon(new ImageIcon(azules.getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(zoomAzules.getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                    }else{
                        aux.setIcon(new ImageIcon(rojas.getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(zoomRojas.getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                    }
                    aux.setText(seed+"");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
                    aux.setForeground(new Color(255, 255, 255));
                    aux.setHorizontalTextPosition(SwingConstants.CENTER);
                    aux.setVerticalTextPosition(SwingConstants.BOTTOM);
                    aux.setVerticalAlignment(SwingConstants.CENTER);
                    aux.setHorizontalAlignment(SwingConstants.LEFT);
                    aux.setBorderPainted(false);
                    cont += 1;
                    add(aux);
                }else if(j == 0 || j == cols-1){
                    JLabel aux = new JLabel(new ImageIcon(imagen.getImage().getScaledInstance((ancho*8/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                    add(aux);
                }else if(j == 1) {
                    JLabel aux = new JLabel();
                    aux.setText("<html><center>Valor en tu almacen: <br>" + valor1 + "</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else if(j == cols-2) {
                    JLabel aux = new JLabel();
                    aux.setText("<html><center>Valor en tu almacen: <br>" + valor2 + "</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else if(j == 2){
                    JLabel aux = new JLabel("<html><center>Has hecho "+ mov1 + " movimientos. ¡Apresúrate a ganar!</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else if(j == cols-3) {
                    JLabel aux = new JLabel("<html><center>Has hecho " + mov2 + " movimientos. ¡Apresúrate a ganar!</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else{
                    JLabel aux = new JLabel(new ImageIcon(imagen3.getImage().getScaledInstance((ancho*8/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }



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
