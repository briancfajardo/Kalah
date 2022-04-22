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
import domain.kalah;

public class KalahGUIFinal extends JFrame implements ActionListener {

    private int ancho = 960;
    private int alto = 540;
    private Fondo fondo = new Fondo();
    private Button aceptar;

    private Button reiniciar;
    private kalah kalah;

    public KalahGUIFinal(kalah k){
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements();
        prepareActions();
        setVisible(true);
        this.kalah = k;

    }

    private void prepareElements(){
        setSize(ancho,alto);
        prepareElementsFinal();

    }

    private void prepareElementsFinal(){
        int ganador = 1;
        int perdedor = 2;
        int semGanador = 0;
        int semPerdedor = 0;

        if (kalah.getContJ1() > kalah.getContJ2()){
            semGanador = kalah.getContJ1();
            semPerdedor = kalah.getContJ2();
        }else{
            ganador = 2;
            perdedor = 1;
            semGanador = kalah.getContJ2();
            semPerdedor = kalah.getContJ1();
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
        mensaje.setFont(new Font("Serif", Font.CENTER_BASELINE, 40));
        mensaje.setBorder(BorderFactory.createEmptyBorder(40,10,30,10));
        mensaje.setOpaque(false);

        panelBotones.setBorder(new CompoundBorder(new EmptyBorder(0,1000,30,1000),
                new TitledBorder("")));
        panelBotones.setLayout(new GridLayout(1,2));
        panelBotones.add(aceptar);
        panelBotones.setOpaque(false);


        add(mensaje);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aceptar){
            KalahGUI game = new KalahGUI();
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
            imagen = new ImageIcon(getClass().getResource("/presentation/podio.jpg")).getImage();
            g.drawImage(imagen,0,0,getWidth(),getHeight(),this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
