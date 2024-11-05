package sistemaTest;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02_RegistrarJugadorTest {

    Retorno retorno;
    static Sistema sistema  =  new ImplementacionSistema();
        
    @BeforeAll
    static void inicializarSistema() {        
        sistema.inicializarSistema(10);
    }


    @Test
    void siAlgunoDeLosParametrosVaciosONull() {
        
        retorno = sistema.registrarJugador("", "Juan", "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

      
        retorno = sistema.registrarJugador("jugador1", "", "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador2", "Juan", "", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarJugador(null, "Juan", "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador3", null, "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador4", "Juan", null, Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador5", "Juan", "Perez", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void siYaExisteJugadorRegistradoConAlias() { 

        
        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador1", "Pedro", "Gomez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador1", "Carlos", "Ramirez", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarJugadorExitosamente() {
        Sistema sistema = new ImplementacionSistema();

        
        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador1", "Maria", "Lopez", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador2", "Ana", "Martinez",Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador3", "Luis", "Fernandez", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void metodoNoImplementado() {
        Sistema sistema = new ImplementacionSistema();

        
        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        
        retorno = sistema.registrarJugador("jugador1", "Maria", "Lopez", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
