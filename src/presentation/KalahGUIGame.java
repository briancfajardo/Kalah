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

/**
 * Clase que genera la ventana principal del juego, contiene las reglas
 * y la interaccción con la lógica dentro del paquete domain
 */
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


    /**
     * Constructor por defecto de la clase KalahGUIGame
     */
    public KalahGUIGame(){
        kalah = new kalah(cols, cantSemillas1);
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    /**
     * Constructor de la clase KalahGUIGame, este constructor acepta modificaciones
     * en color de fondo, color de las semillas, número de semillas y cantidad de casas
     * para ambos jugadores
     *
     * @param color1
     * @param color2
     * @param sem1
     * @param sem2
     * @param numSem
     * @param numCas
     */
    public KalahGUIGame(Color color1, Color color2, String sem1, String sem2, int numSem, int numCas){
        kalah = new kalah(numCas, numSem);
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements(color1, color2, sem1, sem2, numSem, numCas);
        prepareActions();
        setVisible(true);
    }

    /**
     * Constructor de la clase KalahGUIGame, este constructor acepta modificaciones
     * en color de fondo, color de las semillas, número de semillas, cantidad de casas
     * para ambos jugadores y un atributo kalah
     *
     * @param color1
     * @param color2
     * @param sem1
     * @param sem2
     * @param numSem
     * @param numCas
     * @param k
     */
    public KalahGUIGame(Color color1, Color color2, String sem1, String sem2, int numSem, int numCas,kalah k){
        this.kalah = k;
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements(color1, color2, sem1, sem2, numSem, numCas);
        prepareActions();
        setVisible(true);
    }

    /**
     * Método que prepara los elementos necesarios para esta pestaña
     */
    private void prepareElements(){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsBoard();

    }

    /**
     * Método que prepara los elementos de la ventana principal con modificaciones según
     * las configuraciones ingresadas
     *
     * @param color1
     * @param color2
     * @param sem1
     * @param sem2
     * @param numSem
     * @param numCas
     */
    private void prepareElements(Color color1, Color color2, String sem1, String sem2, int numSem, int numCas){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsBoard(color1, color2, sem1, sem2, numSem, numCas);

    }

    /**
     * Genera todos los elementos del menú, la barra de JMenuBar y sus items,
     * asignándole a cada item su propio oyente
     */
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

    /**
     * Genera los elementos necesarios para crear el tablero del juego
     * Este tablero es el tablero por defecto, con tres semillas en
     * cada casa (6 casas)
     * El tablero contiene las casas de cada jugador, los dos almacenes
     * y los mensajes que definen el número de movimientos y la cantidad de
     * semillas en cada contenedor de ambos jugadores
     */
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

    /**
     * Genera los elementos necesarios para crear el tablero del juego

     * El tablero contiene las casas de cada jugador, los dos almacenes
     * y los mensajes que definen el número de movimientos y la cantidad de
     * semillas en cada contenedor de ambos jugadores
     *
     * El tablero se modifica según los parámetros de entrada
     *
     * La cantidad de semillas para cada cada de los juadores se modifica según el
     * atributo kalah. En caso de que no sea el turno del jugador, se le
     * deshabilitarán los botones de sus casas, asimismo, si no hay semillas en una casa
     *
     * @param color1
     * @param color2
     * @param colSem1
     * @param colSem2
     * @param numSem
     * @param numCas
     */
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
        }
        kalah.cambioTurno();
    }

    /**
     * Cambia el atributo de la clase por los colores ingresados como parámetro
     * para el jugador especificado
     *
     * El atributo corresponde al fondo de las casas del jugador
     *
     * @param color
     * @param jugador
     */
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

    /**
     * Cambia el número de semillas para los jugadores
     *
     * @param cantidad
     * @param jugador
     */
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

    /**
     * Obtiene la imagen correspondiente al contenedor o casa necesaria según
     * el color de sus semillas y su número
     *
     * Estas imágenes están guardadas dentro del paquete presentation
     *
     * @param numSemillas
     * @param color
     * @return new ImageIcon(getClass().getResource(dir)
     */
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

    /**
     * Obtiene la imagen del zoom correspondiente al contenedor o casa necesaria según
     * el color de sus semillas y su número
     *
     * Estas imágenes están guardadas dentro del paquete presentation
     *
     * @param numSemillas
     * @param color
     * @return new ImageIcon(getClass().getResource(dir)
     */
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

    /**
     * Cambia la primera letra por su mayúscula
     *
     * @param palabra
     * @return palabra
     */
    private String mayusL(String palabra){
        String firstLtr = palabra.substring(0, 1);
        String restLtrs = palabra.substring(1, palabra.length());

        firstLtr = firstLtr.toUpperCase();
        palabra = firstLtr + restLtrs;
        return palabra;
    }

    /**
     * Crea la nueva pestaña de la misma clase según la variación del
     * kalah
     */
    private void refresh(){
        KalahGUIGame game = new KalahGUIGame(color1, color2, semillas1, semillas2, cantSemillas1, cols, kalah);
        game.setVisible(true);
        game.setResizable(false);
        game.setLocationRelativeTo(null);
        dispose();
    }

    /**
     * Modifica la acción de cerrar la pestaña
     * para confirmar la salida
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
     * Genera la nueva pestaña de confirmación para verificar
     * la salida de la pestaña actual
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
        if(e.getSource() == reiniciar){
            KalahGUIGame game = new KalahGUIGame(color1, color2, semillas1, semillas2, cantSemillas1, cols);
            game.setVisible(true);
            game.setResizable(false);
            game.setLocationRelativeTo(null);
            dispose();
        }

    }

    /**
     * Clase que contiene los eventos que modelan la lógica del juego
     */
    class EventosCasitas implements ActionListener{
        private int x;
        private int y;

        /**
         * Método que genera una nueva pestaña de tipo KalahGUIFinal que genera el mensaje
         * de finalización, señalando al ganador y el contenido de cada
         * contenedor
         */
        public void finaliza(){
            KalahGUIFinal fin = new KalahGUIFinal(kalah.getContJ1(), kalah.getContJ2());
            fin.setVisible(true);
            fin.setResizable(false);
            fin.setLocationRelativeTo(null);
            dispose();

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            kalah.movimientoJug(x,y);
            if (kalah.getFin()){
                finaliza();
            }else{
                refresh();
            }
        }

        /**
         * Modifica las coordenas según los parámetros
         * @param x
         * @param y
         */
        public void setCords(int x, int y){
            this.x = x;
            this.y = y;
        }

        /**
         * Obtiene el atributo entero x
         * @return x
         */
        public int getX(){
            return x;
        }

        /**
         * Obtiene el atributo entero y
         * @return y
         */
        public int getY() {
            return y;
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
