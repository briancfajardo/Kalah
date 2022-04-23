package domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class kalahTest extends kalah {

    public kalahTest() {
        super(6,3);
    }
    @Test
    void ShouldMovimientoJug() {
        super.movimientoJug(0,0);
        ArrayList<Integer> auxTest = new ArrayList<Integer>(List.of(0,3,3,3,3,3));
        ArrayList<Integer> auxTest2 = new ArrayList<Integer>(List.of(1,0,0,0,0,0));
        ArrayList<Integer> auxTest3 = new ArrayList<Integer>(List.of(4,4,3,3,3,3));
        assertEquals(auxTest, super.jugador1);
        assertEquals(auxTest2, super.contenedores);
        assertEquals(auxTest3, super.jugador2);
    }

    @Test
    void ShouldReglasRobarTurnoYFichas() {
        super.movimientoJug(0,2);
        super.cambioTurno();
        super.movimientoJug(0,5);
        ArrayList<Integer> auxTest = new ArrayList<Integer>(List.of(4,4,0,4,4,0));
        ArrayList<Integer> auxTest2 = new ArrayList<Integer>(List.of(5,0,0,0,0,0));
        ArrayList<Integer> auxTest3 = new ArrayList<Integer>(List.of(3,3,0,3,3,3));
        assertEquals(auxTest, super.jugador1);
        assertEquals(auxTest2, super.contenedores);
        assertEquals(auxTest3, super.jugador2);
    }

    @Test
    void ShouldCambioTurno() {
        super.cambioTurno();
        assertEquals(1, super.getTurno());
    }

    @Test
    void ShouldFinalizaJuego() {
        super.movimientoJug(0,2);
        super.cambioTurno();
        super.movimientoJug(0,5);
        super.cambioTurno();
        super.movimientoJug(2,3);
        super.cambioTurno();
        super.movimientoJug(2,0);
        super.cambioTurno();
        super.movimientoJug(0,4);
        super.cambioTurno();
        super.movimientoJug(2,2);
        super.cambioTurno();
        super.movimientoJug(0,0);
        super.cambioTurno();
        super.movimientoJug(2,2);
        super.cambioTurno();
        super.movimientoJug(0,1);
        super.cambioTurno();
        super.movimientoJug(2,0);
        super.cambioTurno();
        super.movimientoJug(0,0);
        super.cambioTurno();
        super.movimientoJug(0,2);
        super.cambioTurno();
        ArrayList<Integer> auxTest = new ArrayList<Integer>(List.of(0,0,0,0,0,0));
        ArrayList<Integer> auxTest2 = new ArrayList<Integer>(List.of(16,0,0,0,0,20));
        ArrayList<Integer> auxTest3 = new ArrayList<Integer>(List.of(0,0,0,0,0,0));
        assertEquals(auxTest, super.jugador1);
        assertEquals(auxTest2, super.contenedores);
        assertEquals(auxTest3, super.jugador2);
    }
}