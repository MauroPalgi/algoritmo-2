package sistemaTest;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test08_ListarJugadorEquipoTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10); 

        
        sistema.registrarJugador("jpro1", "Juan", "Pérez", Categoria.PROFESIONAL);
        sistema.registrarJugador("jest1", "Luis", "Martínez",  Categoria.ESTANDARD);
        sistema.registrarJugador("jpro2", "Ana", "Gómez",  Categoria.PROFESIONAL);
        sistema.registrarEquipo("Equipo1", "Carlos Martínez");

        sistema.agregarJugadorAEquipo("Equipo1", "jpro1");
        sistema.agregarJugadorAEquipo("Equipo1", "jpro2");
    }

    @Test
    void listarJugadoresDeEquipoExitosamente() { 
        
        retorno = sistema.listarJugadoresDeEquipo("Equipo1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("jpro1;Juan;Pérez;Profesional|jpro2;Ana;Gómez;Profesional", retorno.getValorString());
        assertEquals(2, retorno.getValorInteger());
    }

    @Test
    void listarJugadoresConNombreDeEquipoVacioONull() {
        
        retorno = sistema.listarJugadoresDeEquipo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.listarJugadoresDeEquipo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void listarJugadoresDeEquipoNoExistente() {
        
        retorno = sistema.listarJugadoresDeEquipo("EquipoInexistente");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void listarJugadoresDeEquipoSinJugadores() { 
        
        sistema.registrarEquipo("Equipo2", "Ana Pérez");
        retorno = sistema.listarJugadoresDeEquipo("Equipo2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
        assertEquals(0, retorno.getValorInteger());
    }
}
