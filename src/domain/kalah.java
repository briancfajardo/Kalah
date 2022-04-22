package domain;

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
    private int turno = 1;
    private int ultX = 0;
    private int ultY = 0;

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
        movimientoJug(x,y);
    }
    public int getContJ1(){
        return contenedores.get(0);
    }
    public int getContJ2(){
        return contenedores.get(cols-1);
    }
    //pl = player, 0 significa jugador 1 y 1 significa jugador 2
    public int getSeeds(int pl,int posSeed){
        if (pl == 0) return jugador1.get(posSeed);
        else return jugador2.get(posSeed);
    }

    public void movimientoJug(int x, int y){
        int moverSemillas = jugador2.get(y);
        int aux = y;
        int p = 2;
        int su = 0;
        if (x == 0) {
            p = 1;
            moverSemillas = jugador1.get(y);
            jugador1.set(y, 0);
        }
        if (x != 0) {
            jugador2.set(y, 0);
        }
        for (int s = 0; s < moverSemillas; s++) {
            imprimir();
            if (p == 1){
                if (aux - 1 > -1){
                    if (su == 0){
                        jugador1.set(aux-1 , jugador1.get(aux - 1) + 1);
                        ultX = 0;
                        ultY = aux-1;
                        aux -= 1;
                    }else {
                        jugador1.set(aux , jugador1.get(aux-1) + 1);
                        su = 0;
                        ultX = 0;
                        ultY = aux;

                    }
                }else if (aux - 1 == -1 && x == 0){
                    contenedores.set(0, contenedores.get(0) + 1);
                    aux = 0;
                    p = 2;
                    su = 1;
                    ultX = 1;
                    ultY = 0;
                }else{
                    s -= 1;
                    aux = 0;
                    p = 2;
                    su = 1;
                }
            } else {
                if (aux + 1 < cols){
                    if(su == 0){
                        jugador2.set(aux+1 , jugador2.get(aux + 1) + 1);
                        ultX = 2;
                        ultY = aux + 1;
                        aux += 1;
                    }else {
                        jugador2.set(aux, jugador2.get(aux) + 1);
                        su = 0;
                        ultX = 2;
                        ultY = aux;
                    }
                }else if (aux + 1 == cols && x != 0){
                    contenedores.set(cols-1, contenedores.get(cols -1) + 1);
                    p = 1;
                    su = 1;
                    ultX = 1;
                    ultY = cols-1;
                    aux = cols - 1;
                }else {
                    aux = cols - 1;
                    p = 1;
                    su = 1;
                    s -= 1;
                }
            }
        }
    }


    public void reglasRobarTurnoYFichas(int fila, int columna){
        if (turno == 1 && fila == 0) {
            if (jugador1.get(columna) == 1){
                jugador1.set(columna, jugador1.get(columna) + jugador2.get(columna));
                jugador2.set(columna, 0);
                cambioTurno();
            }
        }else if (turno == 2 && fila == 2){
            if (jugador2.get(columna) == 1){
                jugador2.set(columna, jugador1.get(columna) + jugador1.get(columna));
                jugador1.set(columna, 0);
                cambioTurno();
            }
        }else if (fila == 1){
            if (columna == 0 && turno == 1){turno = 1;}
            else if (columna == cols-1 && turno == 2){turno = 2;}
        }
    }

    public void setUltimaPosicion(int newX, int newY){
        ultX = newX;
        ultY = newY;
    }

    public void cambioTurno(){
        if (turno == 1){
            turno = 2;
        }else{
            turno = 1;
        }
    }

    public void finalizaJuego(){
        int totalJ1 = semillasJugador(jugador1);
        int totalJ2 = semillasJugador(jugador2);
        if (totalJ1 == 0 && ultY == 0 && ultX == 1){
            contenedores.set(0,contenedores.get(0)+ totalJ2);
            jugador2.replaceAll(ignored -> 0);
        }else if (totalJ2 == 0 && ultY == cols-1 && ultX == 1){
            contenedores.set(cols-1,contenedores.get(cols-1) + totalJ1);
            jugador1.replaceAll(ignored -> 0);
        }else if (totalJ1 == 0 && ultY != 0 && ultX != 1){
            contenedores.set(cols-1,contenedores.get(cols-1)+ totalJ2);
            jugador2.replaceAll(ignored -> 0);
        }else if (totalJ2 == 0 && ultY != cols-1 && ultX != 1){
            contenedores.set(0,contenedores.get(0) + totalJ1);
            jugador1.replaceAll(ignored -> 0);
        }
    }
    private  int semillasJugador(ArrayList<Integer> jugador){
        int suma = 0;
        for (Integer i : jugador){
            suma += i.intValue();
        }
        return suma;
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
        System.out.println("_______");
        System.out.println(ultX);
        System.out.println(ultY);
        System.out.println("_Camilo es feo_");
    }

    public static void main(String[] arg){
        kalah gui = new kalah(6,20);
        gui.vaciarCasa(0,2);
        gui.imprimir();
    }
//:(

}