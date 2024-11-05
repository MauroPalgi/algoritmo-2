package sistemaTest;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06_RegistrarEquipoTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10); 
    }

    @Test
    void registrarEquipoExitosamente() {
        
        retorno = sistema.registrarEquipo("Los Halcones", "Carlos Martínez");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarEquipoConNombreDuplicado() {
        
        sistema.registrarEquipo("Los Halcones", "Carlos Martínez"); 

        
        retorno = sistema.registrarEquipo("Los Halcones", "Ana Pérez");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarEquipoConParametrosVaciosONull() {
        
        retorno = sistema.registrarEquipo("", "Carlos Martínez");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarEquipo("Los Leones", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarEquipo(null, "Carlos Martínez");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        
        retorno = sistema.registrarEquipo("Los Tigres", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void metodoNoImplementado() {  
        
        retorno = sistema.registrarEquipo("Los Jaguares", "Luis Suarez");
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
