package sistemaTest;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test07_AgregarJugadorEquipoTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10); 

        
        sistema.registrarJugador("jpro1", "Juan", "Pérez", Categoria.PROFESIONAL);
        sistema.registrarJugador("jest1", "Luis", "Martínez", Categoria.ESTANDARD);
        sistema.registrarJugador("jpro2", "Ana", "Gómez", Categoria.PROFESIONAL);

        sistema.registrarEquipo("Equipo1", "Carlos Martínez");
    }

    @Test
    void agregarJugadorAEquipoExitosamente() { 
        
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jpro1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void agregarJugadorConParametrosVaciosONull() { 
        
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
        
        retorno = sistema.agregarJugadorAEquipo("EquipoInexistente", "jpro1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void agregarJugadorNoExistenteAEquipo() {
        
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jugadorInexistente");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void agregarJugadorQueNoEsProfesionalAEquipo() { 
        
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jest1"); 
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void agregarJugadorAEquipoQueYaTieneCincoJugadores() { 
        
        sistema.registrarJugador("jpro3", "Pedro", "López", Categoria.PROFESIONAL);
        sistema.registrarJugador("jpro4", "Carla", "Rodríguez", Categoria.PROFESIONAL);
        sistema.registrarJugador("jpro5", "Andrés", "Díaz", Categoria.PROFESIONAL);
        sistema.registrarJugador("jpro6", "Lucía", "Vega", Categoria.PROFESIONAL);

        sistema.agregarJugadorAEquipo("Equipo1", "jpro1");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro2");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro3");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro4");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro5");

        
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jpro6");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado()); 
    }

    @Test
    void agregarJugadorQueYaPerteneceAOtroEquipo() { 
        
        sistema.registrarEquipo("Equipo2", "Ana Pérez");
        sistema.agregarJugadorAEquipo("Equipo2", "jpro2");

        
        retorno = sistema.agregarJugadorAEquipo("Equipo1", "jpro2");
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado()); 
    }
}
