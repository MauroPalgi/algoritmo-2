package sistemaTest;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04_ListarJugadorTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        
        sistema.registrarJugador("carlos", "Carlos", "Lopez", Categoria.PRINCIPIANTE);
        sistema.registrarJugador("ana", "Ana", "Gomez", Categoria.PROFESIONAL);
        sistema.registrarJugador("juan", "Juan", "Perez", Categoria.ESTANDARD);
    }

    @Test
    void listarJugadoresAscendenteConJugadoresRegistrados() {
        
        retorno = sistema.listarJugadoresAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ana;Ana;Gomez;Profesional|carlos;Carlos;Lopez;Principiante|juan;Juan;Perez;Estándar", retorno.getValorString());
    }

    @Test
    void listarJugadoresAscendenteSinJugadores() { 
        
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        
        retorno = sistema.listarJugadoresAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString()); 
    }

    @Test
    void metodoNoImplementado() { 
        
        retorno = sistema.listarJugadoresAscendente();
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
