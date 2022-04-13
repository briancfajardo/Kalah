package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class saveColor extends JFrame implements ChangeListener,ActionListener{
    JColorChooser selColor;
    JButton boton;

    public saveColor() {
        selColor = new JColorChooser();
        boton = new JButton("Pulsa aquí para cerrar");
        //Obtener el color escogido
        selColor.getSelectionModel().addChangeListener(this);
        boton.addActionListener(this);
        //Tamaño del panel
        selColor.setMaximumSize(new Dimension(100,100));

        setLayout(new BorderLayout());
        add(selColor, BorderLayout.CENTER);
        add(boton, BorderLayout.SOUTH);

        pack();
        prepareActions();
    }

    public void stateChanged(ChangeEvent e){
        boton.setForeground(selColor.getColor());
    }

    public void actionPerformed(ActionEvent e){
        close();
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

    public static void main(String[] args) {
        new saveColor().setVisible(true);
    }
}
