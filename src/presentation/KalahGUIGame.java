package presentation;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import domain.kalah;

public class KalahGUIGame extends JFrame implements ActionListener {

    private int ancho = 960;
    private int alto = 540;
    private int rows = 3;
    private int cols = 6;
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
    private Button config;
    private Button reiniciar;
    private JFileChooser archivos;
    private File partida;
    private Fondo fondo = new Fondo();

    private ImageIcon imagen = new ImageIcon(getClass().getResource("/presentation/bola.png"));
    private ImageIcon imagen3 = new ImageIcon(getClass().getResource("/presentation/transparente.png"));
    private ImageIcon jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasAzul.png"));
    private ImageIcon zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasAzul.png"));
    private ImageIcon jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasRoja.png"));
    private ImageIcon zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasRoja.png"));
    private Color color1;
    private Color color2;
    private String semillas1 = "azul";
    private String semillas2 = "roja";
    private int cantSemillas1 = 3;
    private int cantSemillas2 = 3;

    private kalah kalah;


    public KalahGUIGame(){
        kalah = new kalah(cols, cantSemillas1);
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    public KalahGUIGame(Color color1, Color color2, String sem1, String sem2, int numSem, int numCas){
        kalah = new kalah(numCas, numSem);
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements(color1, color2, sem1, sem2, numSem, numCas);
        prepareActions();
        setVisible(true);
    }

    public KalahGUIGame(Color color1, Color color2, String sem1, String sem2, int numSem, int numCas,kalah k){
        this.kalah = k;
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements(color1, color2, sem1, sem2, numSem, numCas);
        prepareActions();
        setVisible(true);
    }

    private void prepareElements(){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsBoard();

    }

    private void prepareElements(Color color1, Color color2, String sem1, String sem2, int numSem, int numCas){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsBoard(color1, color2, sem1, sem2, numSem, numCas);

    }

    private void prepareElementsMenu(){
        menu = new JMenuBar();
        setJMenuBar(menu);
        archivoM = new JMenu("Archivo");
        archivoM.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menu.add(archivoM);
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

        JPanel nuevoPanel = new JPanel();

        config = new Button("Configuración");
        config.setCursor(new Cursor(Cursor.HAND_CURSOR));
        config.addActionListener(this);

        reiniciar = new Button("Reiniciar");
        reiniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reiniciar.addActionListener(this);

        nuevoPanel.add(config);
        nuevoPanel.add(reiniciar);
        menu.add(nuevoPanel);


    }
    //Carga de imagenes

    private void prepareElementsBoard(){
        setLayout(new GridLayout(rows,cols));
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                if (i != 1) {
                    JButton aux = new JButton();
                    EventosCasitas evento = new EventosCasitas();
                    evento.setCords(i,j);
                    aux.setOpaque(true);
                    aux.setContentAreaFilled(false);
                    if (i == 0) {
                        aux.setIcon(new ImageIcon(getImage(kalah.getSeeds(i,j), semillas1).getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(zoomjugador1.getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                    }else{
                        aux.setIcon(new ImageIcon(getImage(kalah.getSeeds(i,j), semillas2).getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(zoomjugador2.getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                        aux.setEnabled(false);
                    }
                    aux.setText(kalah.getSeeds(i,j)+"");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
                    aux.setForeground(new Color(255, 255, 255));
                    aux.setHorizontalTextPosition(SwingConstants.CENTER);
                    aux.setVerticalTextPosition(SwingConstants.BOTTOM);
                    aux.setVerticalAlignment(SwingConstants.CENTER);
                    aux.setHorizontalAlignment(SwingConstants.LEFT);
                    aux.setBorderPainted(false);
                    aux.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    aux.addActionListener(evento);
                    add(aux);
                }else if(j == 0 || j == cols-1){
                    JLabel aux = new JLabel(new ImageIcon(imagen.getImage().getScaledInstance((ancho*8/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                    add(aux);
                }else if(j == 1) {
                    JLabel aux = new JLabel();
                    aux.setText("<html><center>Valor en tu almacen: <br>" + kalah.getContJ1() + "</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else if(j == cols-2) {
                    JLabel aux = new JLabel();
                    aux.setText("<html><center>Valor en tu almacen: <br>" + kalah.getContJ2() + "</center><html>");
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
        }kalah.cambioTurno();
    }


    private void prepareElementsBoard(Color color1, Color color2, String colSem1, String colSem2, int numSem, int numCas){
        this.color1 = color1;
        this.color2 = color2;
        numSemilla(numSem, 1);
        numSemilla(numSem, 2);
        colorSemilla(colSem1, 1);
        colorSemilla(colSem2, 2);
        cols = numCas;

        setLayout(new GridLayout(rows,cols));
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                if (i != 1) {
                    JButton aux = new JButton();
                    EventosCasitas evento = new EventosCasitas();
                    evento.setCords(i,j);
                    aux.setOpaque(true);
                    aux.setContentAreaFilled(false);
                    if (i == 0) {
                        if (color1 != null ){
                            aux.setContentAreaFilled(true);
                            aux.setBackground(color1);
                        }
                        //validadorImagenesJug1(kalah.getSeeds(i,j));
                        aux.setIcon(new ImageIcon(getImage(kalah.getSeeds(i,j), semillas1).getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(getImageZoom(kalah.getSeeds(i,j), semillas1).getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                        if(kalah.getTurno() == 1){
                            aux.setEnabled(false);
                        }else if (kalah.getSeeds(i,j) == 0){
                            aux.setEnabled(false);
                        }
                    }else{
                        if (color2 != null ){
                            aux.setContentAreaFilled(true);
                            aux.setBackground(color2);
                        }
                        //validadorImagenesJug2(kalah.getSeeds(i,j));
                        aux.setIcon(new ImageIcon(getImage(kalah.getSeeds(i,j), semillas2).getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(getImageZoom(kalah.getSeeds(i,j), semillas2).getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                        if(kalah.getTurno() == 2){
                            aux.setEnabled(false);
                        }else if (kalah.getSeeds(i,j) == 0){
                            aux.setEnabled(false);
                        }
                    }
                    aux.setText(kalah.getSeeds(i,j)+"");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
                    aux.setForeground(new Color(255, 255, 255));
                    aux.setHorizontalTextPosition(SwingConstants.CENTER);
                    aux.setVerticalTextPosition(SwingConstants.BOTTOM);
                    aux.setVerticalAlignment(SwingConstants.CENTER);
                    aux.setHorizontalAlignment(SwingConstants.LEFT);
                    aux.setBorderPainted(false);
                    aux.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    aux.addActionListener(evento);
                    add(aux);
                }else if(j == 0){
                    JLabel aux = new JLabel(new ImageIcon(getImage(kalah.getContJ1(), semillas1).getImage().getScaledInstance((ancho*8/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                    add(aux);
                }else if(j == cols-1) {
                    JLabel aux = new JLabel(new ImageIcon(getImage(kalah.getContJ2(), semillas2).getImage().getScaledInstance((ancho * 8 / 9) / cols, (alto * 3 / 5) / rows, Image.SCALE_SMOOTH)));
                    add(aux);
                }else if(j == 1) {
                    JLabel aux = new JLabel();
                    aux.setText("<html><center>Valor en tu almacen: <br>" + kalah.getContJ1() + "</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else if(j == cols-2) {
                    JLabel aux = new JLabel();
                    aux.setText("<html><center>Valor en tu almacen: <br>" + kalah.getContJ2() + "</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else if(j == 2){
                    JLabel aux = new JLabel("<html><center>Has hecho "+ kalah.getMov1() + " movimientos. ¡Apresúrate a ganar!</center><html>");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 20*6/cols));
                    aux.setForeground(new Color(255, 255, 255));
                    add(aux);
                }else if(j == cols-3) {
                    JLabel aux = new JLabel("<html><center>Has hecho " + kalah.getMov2() + " movimientos. ¡Apresúrate a ganar!</center><html>");
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
        }kalah.cambioTurno();

    }

    public void colorSemilla(String color, int jugador){
        //Colores posibles: azul, cafe, amarillo, gris, morado, negro, rosado, verde, rojo
        if (jugador == 1){
            this.semillas1 = color;
        }else if (jugador == 2){
            this.semillas2 = color;
        }else{
            JOptionPane.showMessageDialog(this,"El jugador no existe\n" ,"colorSemilla",
                    1,null);
        }
    }

    public void numSemilla(int cantidad, int jugador) {
        //Numeros posibles: 0,1,2,3,...
        if (jugador == 1) {
            cantSemillas1 = cantidad;
        } else if (jugador == 2) {
            cantSemillas2 = cantidad;
        } else {
            JOptionPane.showMessageDialog(this, "El jugador no existe\n", "colorSemilla",
                    1, null);
        }
    }

    private  ImageIcon getImage(int numSemillas, String color){
        color = mayusL(color);
        String dir = "/presentation/"+numSemillas+"semillas"+color+".png";
        if (numSemillas == 0){
            dir = "/presentation/bola.png";
        } else if (numSemillas > 8) {
            dir = "/presentation/8semillas"+color+".png";
        }
        return new ImageIcon(getClass().getResource(dir));
    }
    private  ImageIcon getImageZoom(int numSemillas, String color){
        color = mayusL(color);
        String dir = "/presentation/zoom"+numSemillas+"semillas"+color+".png";
        if (numSemillas == 0){
            dir = "/presentation/bola2.png";
        }else if (numSemillas > 8) {
            dir = "/presentation/zoom8semillas"+color+".png";
        }
        return new ImageIcon(getClass().getResource(dir));
    }
    private String mayusL(String palabra){
        String firstLtr = palabra.substring(0, 1);
        String restLtrs = palabra.substring(1, palabra.length());

        firstLtr = firstLtr.toUpperCase();
        palabra = firstLtr + restLtrs;
        return palabra;
    }
    private void refresh(){
        KalahGUIGame game = new KalahGUIGame(color1, color2, semillas1, semillas2, cantSemillas1, cols, kalah);
        game.setVisible(true);
        game.setResizable(false);
        game.setLocationRelativeTo(null);
        dispose();
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
        if(e.getSource() == config){
            KalahGUIConfig game = new KalahGUIConfig();
            game.setVisible(true);
            game.setResizable(false);
            game.setLocationRelativeTo(null);
            dispose();
        }
        if(e.getSource() == reiniciar){
            KalahGUIGame game = new KalahGUIGame(color1, color2, semillas1, semillas2, cantSemillas1, cols);
            game.setVisible(true);
            game.setResizable(false);
            game.setLocationRelativeTo(null);
            dispose();
        }

    }
    class EventosCasitas implements ActionListener{
        private int x;
        private int y;
        @Override
        public void actionPerformed(ActionEvent e) {
            kalah.movimientoJug(x,y);
            refresh();
        }
        public void setCords(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        public int getY() {
            return y;
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
