package sistemaTest;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09_ListarEquipoDescendentesTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10); 

        
        sistema.registrarEquipo("Alpha", "Manager A");
        sistema.registrarEquipo("Gamma", "Manager C");
        sistema.registrarEquipo("Beta", "Manager B");
        sistema.registrarEquipo("Delta", "Manager D");

        
        sistema.agregarJugadorAEquipo("Alpha", "jpro1");
        sistema.agregarJugadorAEquipo("Beta", "jpro2");
        sistema.agregarJugadorAEquipo("Gamma", "jpro3");
    }

    @Test
    void listarEquiposDescendentemente() { 
        
        retorno = sistema.listarEquiposDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Gamma;Manager C;1|Beta;Manager B;1|Alpha;Manager A;1|Delta;Manager D;0", retorno.getValorString());
    }

    @Test
    void listarEquiposSinEquiposRegistrados() { 
        
        sistema = new ImplementacionSistema();
        retorno = sistema.listarEquiposDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString()); 
    }

    @Test
    void listarEquiposConNombresDuplicados() { 
        
        sistema.registrarEquipo("Alpha", "Manager A");
        retorno = sistema.listarEquiposDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        assertEquals("Gamma;Manager C;1|Beta;Manager B;1|Alpha;Manager A;1|Delta;Manager D;0|Alpha;Manager A;0", retorno.getValorString());
    }
}
