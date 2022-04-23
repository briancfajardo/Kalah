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
    }

    @Test
    void ShouldSetUltimaPosicion() {
    }

    @Test
    void ShouldCambioTurno() {
    }

    @Test
    void ShouldFinalizaJuego() {
    }
}