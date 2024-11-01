package sistemaTest;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04_ListarJugadorTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        // Registrar jugadores para las pruebas
        sistema.registrarJugador("carlos", "Carlos", "Lopez", Categoria.PRINCIPIANTE);
        sistema.registrarJugador("ana", "Ana", "Gomez", Categoria.PROFESIONAL);
        sistema.registrarJugador("juan", "Juan", "Perez", Categoria.ESTANDARD);
    }

    @Test
    void listarJugadoresAscendenteConJugadoresRegistrados() {
        // Caso: Listado con jugadores registrados
        retorno = sistema.listarJugadoresAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ana;Ana;Gomez;Profesional|carlos;Carlos;Lopez;Principiante|juan;Juan;Perez;Estándar", retorno.getValorString());
    }

    @Test
    void listarJugadoresAscendenteSinJugadores() { // TODO: ERRORES - Mauro - Done
        // Inicializar sistema sin jugadores
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        // Caso: No hay jugadores registrados
        retorno = sistema.listarJugadoresAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString()); // Se espera una cadena vacía si no hay jugadores
    }

    @Test
    void metodoNoImplementado() { // TODO: ERRORES - Constanza
        // Simulación de metodo aún no implementado
        retorno = sistema.listarJugadoresAscendente();
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
