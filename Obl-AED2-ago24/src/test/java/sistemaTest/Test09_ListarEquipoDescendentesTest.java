package sistemaTest;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09_ListarEquipoDescendentesTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10); // Inicializar el sistema con capacidad para 10 equipos

        // Registrar algunos equipos de prueba
        sistema.registrarEquipo("Alpha", "Manager A");
        sistema.registrarEquipo("Gamma", "Manager C");
        sistema.registrarEquipo("Beta", "Manager B");
        sistema.registrarEquipo("Delta", "Manager D");

        // Agregar algunos jugadores a los equipos para el contexto
        sistema.agregarJugadorAEquipo("Alpha", "jpro1");
        sistema.agregarJugadorAEquipo("Beta", "jpro2");
        sistema.agregarJugadorAEquipo("Gamma", "jpro3");
    }

    @Test
    void listarEquiposDescendentemente() { // TODO: ERRORES - Mauro
        // Caso: Listar los equipos ordenados por nombre de forma descendente
        retorno = sistema.listarEquiposDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Gamma;Manager C;1|Beta;Manager B;1|Alpha;Manager A;1|Delta;Manager D;0", retorno.getValorString());
    }

    @Test
    void listarEquiposSinEquiposRegistrados() { // TODO: ERRORES - Constanza
        // Caso: No hay equipos registrados
        sistema = new ImplementacionSistema();
        retorno = sistema.listarEquiposDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString()); // Cadena vacía porque no hay equipos
    }

    @Test
    void listarEquiposConNombresDuplicados() { // TODO: ERRORES - Mauro
        // Caso: Listar equipos con nombres duplicados
        sistema.registrarEquipo("Alpha", "Manager A");
        retorno = sistema.listarEquiposDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        // Debe manejar correctamente el duplicado, dependiendo de la implementación
        assertEquals("Gamma;Manager C;1|Beta;Manager B;1|Alpha;Manager A;1|Delta;Manager D;0|Alpha;Manager A;0", retorno.getValorString());
    }
}
