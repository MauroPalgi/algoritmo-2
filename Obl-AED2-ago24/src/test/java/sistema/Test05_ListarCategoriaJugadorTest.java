package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05_ListarCategoriaJugadorTest {

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
        sistema.registrarJugador("pedro", "Pedro", "Martinez", Categoria.PRINCIPIANTE);
        sistema.registrarJugador("luis", "Luis", "Suarez", Categoria.PROFESIONAL);
    }

    @Test
    void listarJugadoresDeCategoriaPrincipiante() {
        // Caso: Listado de jugadores en la categoría "PRINCIPIANTE"
        retorno = sistema.listarJugadoresPorCategoria(Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("carlos;Carlos;Lopez;PRINCIPIANTE|pedro;Pedro;Martinez;PRINCIPIANTE", retorno.getValorString());
    }

    @Test
    void listarJugadoresDeCategoriaProfesional() {
        // Caso: Listado de jugadores en la categoría "PROFESIONAL"
        retorno = sistema.listarJugadoresPorCategoria(Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("ana;Ana;Gomez;PROFESIONAL|luis;Luis;Suarez;PROFESIONAL", retorno.getValorString());
    }

    @Test
    void listarJugadoresDeCategoriaEstandar() {
        // Caso: Listado de jugadores en la categoría "ESTANDARD"
        retorno = sistema.listarJugadoresPorCategoria(Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("juan;Juan;Perez;ESTANDARD", retorno.getValorString());
    }

  /*  @Test // Mauro
    void listarJugadoresDeCategoriaSinJugadores() {
        // Caso: No hay jugadores registrados en la categoría "EXPERTO" (suponiendo que no existe tal categoría)
        retorno = sistema.listarJugadoresPorCategoria((Categoria)"Experto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString()); // No hay jugadores en esta categoría
    }*/

    @Test
    void metodoNoImplementado() {
        // Simulación de método aún no implementado
        retorno = sistema.listarJugadoresPorCategoria(Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.NO_IMPLEMENTADA, retorno.getResultado());
    }
}
