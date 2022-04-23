package presentation;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class KalahGUIFinal extends JFrame implements ActionListener {

    private int ancho = 960;
    private int alto = 540;
    private KalahGUIFinal.Fondo fondo = new KalahGUIFinal.Fondo();
    private Button aceptar;

    private Button reiniciar;
    private int jug1;
    private int jug2;

    private JMenuBar menu;
    private JMenu archivoM;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salir;
    private JFileChooser archivos;
    private File partida;

    public KalahGUIFinal(int jug1, int jug2){
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements(jug1, jug2);
        prepareActions();
        setVisible(true);
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    private void prepareElements(int jug1, int jug2){
        setSize(ancho,alto);
        prepareElementsMenu();
        prepareElementsFinal(jug1, jug2);

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

    private void prepareElementsFinal(int jug1, int jug2){
        int ganador = 1;
        int perdedor = 2;
        int semGanador = jug1;
        int semPerdedor = jug2;


        if (jug1 < jug2){
            ganador = 2;
            perdedor = 1;
            semGanador = jug2;
            semPerdedor = jug1;
        }

        JLabel mensaje = new JLabel("<html><center>El juego ha acabado, <br> " +
                "ya no quedan más semillas. <br> " + "El jugador " + ganador +
                " ha ganado <br> ¡Felicitaciones! <br>" +
                "Obtuviste " + semGanador + " semillas vs. " + semPerdedor + " semillas. <br>" +
                "Jugador " + perdedor + " suerte para la próxima </center><html>");
        aceptar = new Button("Aceptar");
        reiniciar = new Button("Reiniciar");

        JPanel panelBotones = new JPanel();

        aceptar.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        aceptar.setSize(30,30);
        aceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        aceptar.addActionListener(this);

        mensaje.setForeground(new Color(0,0,0));
        mensaje.setFont(new Font("Serif", Font.CENTER_BASELINE, 30));
        mensaje.setBorder(BorderFactory.createEmptyBorder(120,10,20,10));
        mensaje.setOpaque(false);

        panelBotones.setBorder(new CompoundBorder(new EmptyBorder(0,1000,30,1000),
                new TitledBorder("")));
        panelBotones.setLayout(new GridLayout(1,2));
        panelBotones.add(aceptar);
        panelBotones.setOpaque(false);


        add(mensaje);
        add(panelBotones);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aceptar){
            KalahGUI game = new KalahGUI();
            game.setVisible(true);
            game.setResizable(false);
            game.setLocationRelativeTo(null);
            dispose();

        }if(e.getSource() == nuevo){
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

    public static void main(String[] arg){
        KalahGUIFinal gui = new KalahGUIFinal(4,5);
        gui.setResizable(false);
        gui.setLocationRelativeTo(null);
    }

    class Fondo extends JPanel{
        private Image imagen;
        @Override
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/presentation/fondoAceptar.jpg")).getImage();
            g.drawImage(imagen,0,0,getWidth(),getHeight(),this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
