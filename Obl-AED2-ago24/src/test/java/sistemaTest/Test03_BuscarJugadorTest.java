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

        
        sistema.registrarJugador("jugador1", "Juan", "Perez", Categoria.ESTANDARD);
        sistema.registrarJugador("jugador2", "Ana", "Gomez", Categoria.PROFESIONAL);
        sistema.registrarJugador("jugador3", "Carlos", "Lopez", Categoria.PRINCIPIANTE);
    }

    @Test
    void buscarJugadorConAliasVacioONull() {
        
        retorno = sistema.buscarJugador("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.buscarJugador(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarJugadorQueNoExiste() {
        
        retorno = sistema.buscarJugador("jugadorInexistente");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void buscarJugadorExitosamente() { 
        
        sistema.listarJugadoresAscendente();
        retorno = sistema.buscarJugador("jugador1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("jugador1;Juan;Perez;Estándar", retorno.getValorString());
        assertEquals(1, retorno.getValorInteger()); 

        
        retorno = sistema.buscarJugador("jugador2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("jugador2;Ana;Gomez;Profesional", retorno.getValorString());
        assertEquals(2, retorno.getValorInteger()); 
    }

    @Test
    void metodoNoImplementado() { 
        
        retorno = sistema.buscarJugador("jugador3");
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
