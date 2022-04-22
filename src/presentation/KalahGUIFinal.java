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

public class KalahGUIFinal extends JFrame implements ActionListener {

    private int ancho = 960;
    private int alto = 540;
    private Fondo fondo = new Fondo();

    public KalahGUIFinal(){
        this.setContentPane(fondo);
        setTitle("Kalah");
        prepareElements();
        setVisible(true);

    }

    private void prepareElements(){
        setSize(ancho,alto);
        prepareElementsFinal();

    }

    private void prepareElementsFinal(){
        JLabel mensaje = new JLabel("¡Felicidades! El jugador _ ha ganado la partida");
        Button aceptar = new Button("Aceptar");
        //Button reiniciar = new Button("Reiniciar");

        JPanel panelBotones = new JPanel();

        aceptar.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        aceptar.setSize(30,30);
        aceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KalahGUI game = new KalahGUI();
                game.setVisible(true);
                game.setResizable(false);
                game.setLocationRelativeTo(null);
                dispose();
            }
        });

        panelBotones.setBorder(new CompoundBorder(new EmptyBorder(100,1000,30,1000),
                new TitledBorder("")));
        panelBotones.setLayout(new GridLayout(1,2));
        panelBotones.add(aceptar);
        panelBotones.setOpaque(false);

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
