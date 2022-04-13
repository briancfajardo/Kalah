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
    private JMenuItem config;
    private JFileChooser archivos;
    private File partida;
    private Fondo fondo = new Fondo();

    private ImageIcon jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasAzul.png"));
    private ImageIcon zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasAzul.png"));
    private ImageIcon jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasRoja.png"));
    private ImageIcon zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasRoja.png"));
    private String semillas1 = "azul";
    private String semillas2 = "rojo";
    private int cantSemillas1 = 3;
    private int cantSemillas2 = 3;


    public KalahGUIGame(){
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    public KalahGUIGame(Color color1, Color color2){
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements(color1, color2);
        prepareActions();
        setVisible(true);
    }

    private void prepareElements(){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsBoard();

    }

    private void prepareElements(Color color1, Color color2){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsBoard(color1, color2);

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

        config = new JMenuItem("Configuración");
        config.addActionListener(this);

        config.setCursor(new Cursor(Cursor.HAND_CURSOR));

        archivoM.add(nuevo);
        archivoM.add(abrir);
        archivoM.add(salvar);
        archivoM.add(salir);
        menu.add(config);


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
                    aux.setCursor(new Cursor(Cursor.HAND_CURSOR));
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


    private void prepareElementsBoard(Color color1, Color color2){
        ImageIcon imagen = new ImageIcon(getClass().getResource("/presentation/bola.png"));
        ImageIcon imagen3 = new ImageIcon(getClass().getResource("/presentation/transparente.png"));
        //ImageIcon semilla3 = new ImageIcon(getClass().getResource("/presentation/3semillas.png"));

        setLayout(new GridLayout(rows,cols));
        int cont = 0;
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                if (i != 1) {
                    JButton aux = new JButton();
                    aux.setOpaque(true);
                    aux.setContentAreaFilled(false);
                    if (i == 0) {
                        if (color1 != null ){
                            aux.setContentAreaFilled(true);
                            aux.setBackground(color1);
                        }
                        aux.setIcon(new ImageIcon(jugador1.getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(zoomjugador1.getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                    }else{
                        if (color2 != null ){
                            aux.setContentAreaFilled(true);
                            aux.setBackground(color2);
                        }
                        aux.setIcon(new ImageIcon(jugador2.getImage().getScaledInstance((ancho*7/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                        aux.setRolloverIcon(new ImageIcon(zoomjugador2.getImage().getScaledInstance((ancho*5/6)/cols,(alto*2/3)/rows,Image.SCALE_SMOOTH)));
                    }
                    aux.setText(seed+"");
                    aux.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
                    aux.setForeground(new Color(255, 255, 255));
                    aux.setHorizontalTextPosition(SwingConstants.CENTER);
                    aux.setVerticalTextPosition(SwingConstants.BOTTOM);
                    aux.setVerticalAlignment(SwingConstants.CENTER);
                    aux.setHorizontalAlignment(SwingConstants.LEFT);
                    aux.setBorderPainted(false);
                    aux.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    cont += 1;
                    add(aux);
                }else if(j == 0){
                    JLabel aux = new JLabel(new ImageIcon(imagen.getImage().getScaledInstance((ancho*8/9)/cols,(alto*3/5)/rows,Image.SCALE_SMOOTH)));
                    add(aux);
                }else if(j == cols-1) {
                    JLabel aux = new JLabel(new ImageIcon(imagen.getImage().getScaledInstance((ancho * 8 / 9) / cols, (alto * 3 / 5) / rows, Image.SCALE_SMOOTH)));
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

    private void colorSemilla(String color, int jugador){
        //Colores posibles: azul, cafe, amarillo, gris, morado, negro, rosado, verde, rojo
        if (jugador == 1){
            semillas1 = color;
        }else if (jugador == 2){
            semillas2 = color;
        }else{
            JOptionPane.showMessageDialog(this,"El jugador no existe\n" ,"colorSemilla",
                    1,null);
        }
    }

    private void validadorImagenesJug1(){
        if (cantSemillas1 == 0){
            jugador1 = new ImageIcon(getClass().getResource("/presentation/bola.png"));
            zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/bola2.png"));

        }else if (cantSemillas1 == 1){
            if (semillas1.equals("café")){
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semilla.png"));
            }else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaAmarilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaAmarilla.png"));
            }else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaAzul.png"));
            }else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaGris.png"));
            }else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaMorada.png"));
            }else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaNegra.png"));
            }else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaRoja.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaRoja.png"));
            }else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaRosada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaRosada.png"));
            }else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/1semillaVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaVerde.png"));
            }

        }else if (cantSemillas1 == 2) {
            if (semillas1.equals("café")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillas.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillas.png"));
            } else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasAmarilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasAmarilla.png"));
            } else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasAzul.png"));
            } else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasGris.png"));
            } else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasMorada.png"));
            } else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasNegra.png"));
            } else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasRoja.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasRoja.png"));
            } else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasRosada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasRosada.png"));
            } else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasVerde.png"));
            }
        }else if (cantSemillas1 == 3) {
            if (semillas1.equals("café")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillas.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillas.png"));
            } else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasAmarilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasAmarilla.png"));
            } else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasAzul.png"));
            } else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasGris.png"));
            } else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasMorada.png"));
            } else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasNegra.png"));
            } else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasRoja.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasRoja.png"));
            } else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/3semillasRosado.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasRosada.png"));
            } else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasVerde.png"));
            }

        }else if (cantSemillas1 == 4) {
            if (semillas1.equals("café")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillas.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillas.png"));
            } else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillasAmarilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasAmarilla.png"));
            } else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillasAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasAzul.png"));
            } else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillasGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasGris.png"));
            } else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillasMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasMorada.png"));
            } else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillasNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasNegra.png"));
            } else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillasRoja.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasRoja.png"));
            } else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/4semillasRosado.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasRosada.png"));
            } else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/2semillasVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasVerde.png"));
            }

        }else if (cantSemillas1 == 5) {
            if (semillas1.equals("café")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillas.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillas.png"));
            } else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasAmarilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasAmarilla.png"));
            } else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasAzul.png"));
            } else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasGris.png"));
            } else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasMorada.png"));
            } else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasNegra.png"));
            } else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasRoja.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasRoja.png"));
            } else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasRosada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasRosada.png"));
            } else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/5semillasVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasVerde.png"));
            }

        }else if (cantSemillas1 == 6) {
            if (semillas1.equals("café")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillas.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillas.png"));
            } else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasAmarillo.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasAmarilla.png"));
            } else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasAzul.png"));
            } else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasGris.png"));
            } else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasMorada.png"));
            } else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasNegra.png"));
            } else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasRojo.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasRoja.png"));
            } else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasRosada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasRosada.png"));
            } else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/6semillasVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasVerde.png"));
            }

        }else if (cantSemillas1 == 7) {
            if (semillas1.equals("café")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillas.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillas.png"));
            } else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasAmarilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasAmarilla.png"));
            } else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasAzul.png"));
            } else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasGris.png"));
            } else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasMorada.png"));
            } else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasNegra.png"));
            } else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasRoja.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasRoja.png"));
            } else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasRosada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasRosada.png"));
            } else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/7semillasVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasVerde.png"));
            }

        }else if (cantSemillas1 >= 8) {
            if (semillas1.equals("café")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillas.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillas.png"));
            } else if (semillas1.equals("amarillo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasAmarilla.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasAmarilla.png"));
            } else if (semillas1.equals("azul")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasAzul.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasAzul.png"));
            } else if (semillas1.equals("gris")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasGris.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasGris.png"));
            } else if (semillas1.equals("morado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasMorada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasMorada.png"));
            } else if (semillas1.equals("negro")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasNegra.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasNegra.png"));
            } else if (semillas1.equals("rojo")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasRoja.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasRoja.png"));
            } else if (semillas1.equals("rosado")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasRosada.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasRosada.png"));
            } else if (semillas1.equals("verde")) {
                jugador1 = new ImageIcon(getClass().getResource("/presentation/8semillasVerde.png"));
                zoomjugador1 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasVerde.png"));
            }
        }
    }


    private void validadorImagenesJug2(){
        if (cantSemillas2  == 0){
            jugador2 = new ImageIcon(getClass().getResource("/presentation/bola.png"));
            zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/bola2.png"));

        }else if (cantSemillas2 == 1){
            if (semillas2.equals("café")){
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semilla.png"));
            }else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaAmarilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaAmarilla.png"));
            }else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaAzul.png"));
            }else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaGris.png"));
            }else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaMorada.png"));
            }else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaNegra.png"));
            }else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaRoja.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaRoja.png"));
            }else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaRosada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaRosada.png"));
            }else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/1semillaVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom1semillaVerde.png"));
            }

        }else if (cantSemillas2 == 2) {
            if (semillas2.equals("café")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillas.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillas.png"));
            } else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasAmarilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasAmarilla.png"));
            } else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasAzul.png"));
            } else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasGris.png"));
            } else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasMorada.png"));
            } else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasNegra.png"));
            } else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasRoja.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasRoja.png"));
            } else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasRosada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasRosada.png"));
            } else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasVerde.png"));
            }
        }else if (cantSemillas2 == 3) {
            if (semillas2.equals("café")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillas.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillas.png"));
            } else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasAmarilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasAmarilla.png"));
            } else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasAzul.png"));
            } else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasGris.png"));
            } else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasMorada.png"));
            } else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasNegra.png"));
            } else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasRoja.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasRoja.png"));
            } else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/3semillasRosado.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom3semillasRosada.png"));
            } else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasVerde.png"));
            }

        }else if (cantSemillas2 == 4) {
            if (semillas2.equals("café")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillas.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillas.png"));
            } else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillasAmarilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasAmarilla.png"));
            } else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillasAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasAzul.png"));
            } else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillasGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasGris.png"));
            } else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillasMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasMorada.png"));
            } else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillasNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasNegra.png"));
            } else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillasRoja.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasRoja.png"));
            } else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/4semillasRosado.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom4semillasRosada.png"));
            } else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/2semillasVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasVerde.png"));
            }

        }else if (cantSemillas2 == 5) {
            if (semillas2.equals("café")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillas.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillas.png"));
            } else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasAmarilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasAmarilla.png"));
            } else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasAzul.png"));
            } else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasGris.png"));
            } else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasMorada.png"));
            } else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasNegra.png"));
            } else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasRoja.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom2semillasRoja.png"));
            } else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasRosada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasRosada.png"));
            } else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/5semillasVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom5semillasVerde.png"));
            }

        }else if (cantSemillas2 == 6) {
            if (semillas2.equals("café")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillas.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillas.png"));
            } else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasAmarillo.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasAmarilla.png"));
            } else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasAzul.png"));
            } else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasGris.png"));
            } else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasMorada.png"));
            } else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasNegra.png"));
            } else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasRojo.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasRoja.png"));
            } else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasRosada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasRosada.png"));
            } else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/6semillasVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom6semillasVerde.png"));
            }

        }else if (cantSemillas2 == 7) {
            if (semillas2.equals("café")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillas.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillas.png"));
            } else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasAmarilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasAmarilla.png"));
            } else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasAzul.png"));
            } else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasGris.png"));
            } else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasMorada.png"));
            } else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasNegra.png"));
            } else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasRoja.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasRoja.png"));
            } else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasRosada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasRosada.png"));
            } else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/7semillasVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom7semillasVerde.png"));
            }

        }else if (cantSemillas2 >= 8) {
            if (semillas2.equals("café")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillas.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillas.png"));
            } else if (semillas2.equals("amarillo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasAmarilla.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasAmarilla.png"));
            } else if (semillas2.equals("azul")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasAzul.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasAzul.png"));
            } else if (semillas2.equals("gris")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasGris.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasGris.png"));
            } else if (semillas2.equals("morado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasMorada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasMorada.png"));
            } else if (semillas2.equals("negro")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasNegra.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasNegra.png"));
            } else if (semillas2.equals("rojo")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasRoja.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasRoja.png"));
            } else if (semillas2.equals("rosado")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasRosada.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasRosada.png"));
            } else if (semillas2.equals("verde")) {
                jugador2 = new ImageIcon(getClass().getResource("/presentation/8semillasVerde.png"));
                zoomjugador2 = new ImageIcon(getClass().getResource("/presentation/zoom8semillasVerde.png"));
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
        if(e.getSource() == config){
            KalahGUIConfig game = new KalahGUIConfig();
            game.setVisible(true);
            game.setResizable(false);
            game.setLocationRelativeTo(null);
            dispose();
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
