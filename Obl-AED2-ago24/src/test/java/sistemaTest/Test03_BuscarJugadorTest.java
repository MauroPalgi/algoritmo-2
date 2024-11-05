package sistemaTest;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test03_BuscarJugadorTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        // Registrar jugadores para las pruebas
        sistema.registrarJugador("jugador1", "Juan", "Perez", Categoria.ESTANDARD);
        sistema.registrarJugador("jugador2", "Ana", "Gomez", Categoria.PROFESIONAL);
        sistema.registrarJugador("jugador3", "Carlos", "Lopez", Categoria.PRINCIPIANTE);
    }

    @Test
    void buscarJugadorConAliasVacioONull() {
        // Caso: alias vacío
        retorno = sistema.buscarJugador("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Caso: alias null
        retorno = sistema.buscarJugador(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarJugadorQueNoExiste() {
        // Caso: jugador con alias inexistente
        retorno = sistema.buscarJugador("jugadorInexistente");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void buscarJugadorExitosamente() { // TODO: REVISAR ERRORES - Mauro - Done
        // Caso: búsqueda exitosa de "jugador1"
        sistema.listarJugadoresAscendente();
        retorno = sistema.buscarJugador("jugador1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("jugador1;Juan;Perez;Estándar", retorno.getValorString());
        assertEquals(1, retorno.getValorInteger()); // Cantidad de elementos recorridos en la búsqueda

        // Caso: búsqueda exitosa de "jugador2"
        retorno = sistema.buscarJugador("jugador2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("jugador2;Ana;Gomez;Profesional", retorno.getValorString());
        assertEquals(2, retorno.getValorInteger()); // Cantidad de elementos recorridos en la búsqueda
    }

    @Test
    void metodoNoImplementado() { // TODO: REVISAR ERRORES - Constanza
        // Simulación de método aún no implementado
        retorno = sistema.buscarJugador("jugador3");
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
