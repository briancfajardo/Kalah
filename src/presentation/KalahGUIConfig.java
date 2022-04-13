package presentation;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class KalahGUIConfig extends JFrame implements ActionListener {

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
    private KalahGUIConfig.Fondo fondo = new KalahGUIConfig.Fondo();
    private Color col1 = null;
    private Color col2 = null;

    public KalahGUIConfig(){
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements();
        prepareActions();
        setVisible(true);
    }
    private void prepareElements(){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsConfig();
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


    }

    private void prepareElementsConfig(){

        JPanel panelBotones = new JPanel();
        Button color1 = new Button("Cambiar colores del jugador 1");
        Button color2 = new Button("Cambiar colores del jugador 2");
        Button aceptar = new Button("Aceptar");

        JLabel titulo = new JLabel("Opciones de configuración");

        titulo.setForeground(new Color(255, 255, 255));
        titulo.setFont(new Font("Serif", Font.CENTER_BASELINE, 70));

        color1.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        color1.setSize(30,30);
        color1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        color1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col1 = JColorChooser.showDialog(KalahGUIConfig.this,"Seleccione un nuevo color...", Color.white);
            }
        });

        color2.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        color2.setSize(30,30);
        color2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        color2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col2 = JColorChooser.showDialog(KalahGUIConfig.this,"Seleccione un nuevo color...", Color.white);

            }
        });

        aceptar.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        aceptar.setSize(30,30);
        aceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (col1 != null || col2 != null){
                    KalahGUIGame game = new KalahGUIGame(col1, col2);
                    game.setResizable(false);
                    game.setLocationRelativeTo(null);
                    dispose();
                }else{
                    KalahGUIGame game = new KalahGUIGame();
                    game.setResizable(false);
                    game.setLocationRelativeTo(null);
                    dispose();
                }
            }
        });

        panelBotones.setBorder(new CompoundBorder(new EmptyBorder(120,1000,30,1000),
                new TitledBorder("")));
        panelBotones.setLayout(new GridLayout(3,1));

        panelBotones.setOpaque(false);
        panelBotones.add(color1);
        panelBotones.add(color2);
        panelBotones.add(aceptar);

        add(titulo);
        add(panelBotones);

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
}
