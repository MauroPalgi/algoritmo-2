package sistemaTest;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06_RegistrarEquipoTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void inicializarSistema() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10); // Inicializar el sistema con capacidad para 10 equipos
    }

    @Test
    void registrarEquipoExitosamente() {
        // Caso: Registro exitoso de un equipo
        retorno = sistema.registrarEquipo("Los Halcones", "Carlos Martínez");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarEquipoConNombreDuplicado() {
        // Caso: Intentar registrar un equipo con un nombre ya existente
        sistema.registrarEquipo("Los Halcones", "Carlos Martínez"); // Primer registro exitoso

        // Segundo intento con el mismo nombre de equipo
        retorno = sistema.registrarEquipo("Los Halcones", "Ana Pérez");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarEquipoConParametrosVaciosONull() {
        // Caso: Nombre del equipo vacío
        retorno = sistema.registrarEquipo("", "Carlos Martínez");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Caso: Manager vacío
        retorno = sistema.registrarEquipo("Los Leones", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Caso: Nombre del equipo nulo
        retorno = sistema.registrarEquipo(null, "Carlos Martínez");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // Caso: Manager nulo
        retorno = sistema.registrarEquipo("Los Tigres", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void metodoNoImplementado() {  // TODO: ERRORES
        // Caso: Simulación de metodo aún no implementado
        retorno = sistema.registrarEquipo("Los Jaguares", "Luis Suarez");
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
