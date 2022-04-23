package domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class kalahTest extends kalah {
    /**
     * Constructor de la clase de testeos la cual usa el constructor de su padre (kalah)
     * para iniciar un juego con 6 casas y 3 semillas por casa
     */
    public kalahTest() {
        super(6,3);
    }

    /**
     * Testo que verifica el caso en qeu el jugador 1 hace un movimiento desde la posicón 0,0, es decir
     * desde su primera casa a la izquierda
     */
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

    /**
     * Testeo que valida el funcionamiento del método encargado de las reglas de robar fichas y turno del contrincante
     */
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

    /**
     * Test que valida funcionamiento del método encargado de hacer el cambio de turno de los jugadores
     */
    @Test
    void ShouldCambioTurno() {
        super.cambioTurno();
        assertEquals(1, super.getTurno());
    }

    /**
     * Test que valida funcionamiento del método encargado de verificar si un juego terminó
     */
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