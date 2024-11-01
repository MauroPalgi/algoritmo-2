package sistemaTest;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
        // Prueba con alias vacío
        retorno = sistema.registrarJugador("", "Juan", "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

      // Prueba con nombre vacío
        retorno = sistema.registrarJugador("jugador1", "", "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Prueba con apellido vacío
        retorno = sistema.registrarJugador("jugador2", "Juan", "", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Prueba con alias null
        retorno = sistema.registrarJugador(null, "Juan", "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Prueba con nombre null
        retorno = sistema.registrarJugador("jugador3", null, "Perez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Prueba con apellido null
        retorno = sistema.registrarJugador("jugador4", "Juan", null, Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Prueba con categoría null
        retorno = sistema.registrarJugador("jugador5", "Juan", "Perez", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void siYaExisteJugadorRegistradoConAlias() { // TODO: REVISAR ERRORES - Constanza

        // Inicializamos el sistema con un número válido de jugadores
        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Registramos el primer jugador con alias "jugador1"
        retorno = sistema.registrarJugador("jugador1", "Pedro", "Gomez", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Intentamos registrar otro jugador con el mismo alias
        retorno = sistema.registrarJugador("jugador1", "Carlos", "Ramirez", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarJugadorExitosamente() {
        Sistema sistema = new ImplementacionSistema();

        // Inicializamos el sistema con un número válido de jugadores
        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Registramos el primer jugador correctamente
        retorno = sistema.registrarJugador("jugador1", "Maria", "Lopez", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Registramos un segundo jugador correctamente
        retorno = sistema.registrarJugador("jugador2", "Ana", "Martinez",Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Registramos un tercer jugador correctamente
        retorno = sistema.registrarJugador("jugador3", "Luis", "Fernandez", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void metodoNoImplementado() {
        Sistema sistema = new ImplementacionSistema();

        // Inicializamos el sistema
        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Si el método aún no está implementado
        retorno = sistema.registrarJugador("jugador1", "Maria", "Lopez", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
