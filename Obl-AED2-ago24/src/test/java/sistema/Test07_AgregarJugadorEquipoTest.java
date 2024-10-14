package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test07_AgregarJugadorEquipoTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10); // Inicializar el sistema con capacidad para 10 equipos y 10 jugadores

        // Registrar algunos jugadores y equipos de prueba
        sistema.registrarJugador("jpro1", "Juan", "Pérez", Categoria.PROFESIONAL);
        sistema.registrarJugador("jest1", "Luis", "Martínez", Categoria.ESTANDARD);
        sistema.registrarJugador("jpro2", "Ana", "Gómez", Categoria.PROFESIONAL);

        sistema.registrarEquipo("Equipo1", "Carlos Martínez");
    }

    @Test
    void agregarJugadorAEquipoExitosamente() { // TODO: ERRORES  - Constanza
        // Caso: Agregar un jugador profesional a un equipo
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jpro1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void agregarJugadorConParametrosVaciosONull() { // TODO: ERRORES - Mauro
        // Caso: Parámetros vacíos o nulos
        retorno = sistema.agregarJugadorAEquipo("", "jpro1");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.agregarJugadorAEquipo("Equipo1", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.agregarJugadorAEquipo(null, "jpro1");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.agregarJugadorAEquipo("Equipo1", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void agregarJugadorAEquipoNoExistente() {
        // Caso: Intentar agregar un jugador a un equipo que no existe
        retorno = sistema.agregarJugadorAEquipo("EquipoInexistente", "jpro1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void agregarJugadorNoExistenteAEquipo() {
        // Caso: Intentar agregar un jugador que no existe a un equipo
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jugadorInexistente");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void agregarJugadorQueNoEsProfesionalAEquipo() { // TODO: ERRORES - Constanza
        // Caso: Intentar agregar un jugador que no es de categoría "Profesional"
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jest1"); // jest1 es Estándar, no Profesional
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void agregarJugadorAEquipoQueYaTieneCincoJugadores() { // TODO: ERRORES - Mauro
        // Agregar 5 jugadores profesionales a un equipo
        sistema.registrarJugador("jpro3", "Pedro", "López", Categoria.PROFESIONAL);
        sistema.registrarJugador("jpro4", "Carla", "Rodríguez", Categoria.PROFESIONAL);
        sistema.registrarJugador("jpro5", "Andrés", "Díaz", Categoria.PROFESIONAL);
        sistema.registrarJugador("jpro6", "Lucía", "Vega", Categoria.PROFESIONAL);

        sistema.agregarJugadorAEquipo("Equipo1", "jpro1");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro2");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro3");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro4");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro5");

        // Intentar agregar un sexto jugador
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jpro6");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado()); // Ya tiene 5 jugadores
    }

    @Test
    void agregarJugadorQueYaPerteneceAOtroEquipo() { // TODO: ERRORES - Constanza
        // Registrar otro equipo y agregar un jugador profesional a él
        sistema.registrarEquipo("Equipo2", "Ana Pérez");
        sistema.agregarJugadorAEquipo("Equipo2", "jpro2");

        // Intentar agregar el mismo jugador a otro equipo
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jpro2");
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado()); // Jugador ya pertenece a Equipo2
    }
}
