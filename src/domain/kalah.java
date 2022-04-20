package domain;

import presentation.KalahGUI;

import java.util.ArrayList;

public class kalah {
    private ArrayList<ArrayList<Integer>> semillas = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> jugador1 = new ArrayList<Integer>();
    private ArrayList<Integer> jugador2 = new ArrayList<Integer>();
    private ArrayList<Integer> contenedores = new ArrayList<Integer>();

    private int rows = 3;
    private int cols;
    private int seeds;
    private int movimientosJugador1 = 0;
    private int movimientosJugador2 = 0;

    public kalah(int cols, int seeds){
        this.cols = cols;
        this.seeds = seeds;

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (i == 0){
                    jugador1.add(j, seeds);
                } else if (i == 2){
                    jugador2.add(j, seeds);
                }else{
                    contenedores.add(0);
                }
            }
        }

        semillas.add(0, jugador1);
        semillas.add(1, contenedores);
        semillas.add(2, jugador2);
    }

    public void vaciarCasa(int x, int y){
        //int x corresponde al jugador
        int moverSemillas = semillas.get(x).get(y);
        int cont = 1;
        if (x == 0){
            jugador1.set(y, 0);
            movimientosJugador1 += 1;
        }else{
            jugador2.set(y, 0);
            movimientosJugador2 += 1;
        }

        for (int s = 1; s <= moverSemillas; s++) {
            if (x == 0) {
                if (y - s > -1) {
                    jugador1.set(y - s, jugador1.get(y - s) + 1);
                } else if (y - s == -1) {
                    contenedores.set(0, contenedores.get(0) + 1);
                } else {
                    jugador2.set(cont-1, jugador2.get(cont-1) + 1);
                    cont += 1;
                }
            } else if (x == 2) {
                if (y + s < cols) {
                    jugador2.set(y + s, jugador2.get(y + s) + 1);
                } else if (y + s == cols) {
                    contenedores.set(cols - 1, contenedores.get(cols - 1) + 1);
                } else {
                    jugador1.set(cols-cont, jugador1.get(cols-cont) + 1);
                    cont += 1;
                }
            }
        }
        semillas.remove(0);
        semillas.add(0, jugador1);
        semillas.remove(1);
        semillas.add(1, contenedores);
        semillas.remove(2);
        semillas.add(2, jugador2);
    }

    public void robarSemillas(){

    }

    public void cambioTurno(){

    }

    public void finalizaJuego(){

    }

    public void imprimir(){
        for (Integer m : jugador1){
            System.out.println(m + "");
        }
        System.out.println("_______");
        for (Integer m : contenedores){
            System.out.println(m + "");
        }
        System.out.println("_______");
        for (Integer m : jugador2){
            System.out.println(m + "");
        }
    }

    public static void main(String[] arg){
        kalah gui = new kalah(7,6);
        gui.vaciarCasa(0,1);
        gui.imprimir();
    }


}
