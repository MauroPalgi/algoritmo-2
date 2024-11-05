package sistemaTest;

import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static sistema.TestUtil.*;

public class Test14_SucursalesTorneo {

    private Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(100);

        s.registrarSucursal(copiarTexto("I"), "Sucursal I");

        s.registrarSucursal(copiarTexto("H"), "Sucursal H");
        s.registrarSucursal(copiarTexto("J"), "Sucursal J");
        s.registrarSucursal(copiarTexto("K"), "Sucursal K");
        s.registrarSucursal(copiarTexto("L"), "Sucursal L");
        s.registrarSucursal(copiarTexto("F"), "Sucursal F");
        s.registrarSucursal(copiarTexto("B"), "Sucursal B");
        s.registrarSucursal(copiarTexto("D"), "Sucursal D");
        s.registrarSucursal(copiarTexto("A"), "Sucursal A");
        s.registrarSucursal(copiarTexto("G"), "Sucursal G");
        s.registrarSucursal(copiarTexto("E"), "Sucursal E");
        s.registrarSucursal(copiarTexto("C"), "Sucursal C");

        
        
        s.registrarConexion("J", "K", 1);
        s.registrarConexion("L", "K", 1);
        s.registrarConexion("L", "M", 1);

    }

    @Test
    public void testOk1() {
        
        

        s.registrarConexion("A", "B", 4);
        s.registrarConexion("B", "C", 8);
        s.registrarConexion("C", "D", 7);
        s.registrarConexion("D", "E", 9);
        s.registrarConexion("E", "F", 10);
        s.registrarConexion("F", "G", 2);
        s.registrarConexion("G", "H", 1);
        s.registrarConexion("H", "A", 8);
        s.registrarConexion("H", "B", 11);
        s.registrarConexion("E", "F", 14);
        s.registrarConexion("C", "I", 2);
        s.registrarConexion("H", "I", 7);
        s.registrarConexion("G", "I", 6);
        s.registrarConexion("C", "F", 4);

        assertOk(s.sucursalesParaTorneo("A", 21), 21, "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E|F;Sucursal F|G;Sucursal G|H;Sucursal H|I;Sucursal I");

        assertOk(s.sucursalesParaTorneo("A", 18), 14,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|F;Sucursal F|G;Sucursal G|H;Sucursal H|I;Sucursal I");
        assertOk(s.sucursalesParaTorneo("A", 10), 9,
                "A;Sucursal A|B;Sucursal B|G;Sucursal G|H;Sucursal H");

        assertOk(s.sucursalesParaTorneo("A", 1), 0,
                "A;Sucursal A");


    }

    @Test
    public void testOk2() {
        
        
        s.registrarConexion("A", "C", 4);
        s.registrarConexion("B", "C", 2);
        s.registrarConexion("B", "D", 7);
        s.registrarConexion("B", "E", 3);
        s.registrarConexion("C", "D", 1);
        s.registrarConexion("C", "E", 6);
        s.registrarConexion("D", "E", 4);
        s.registrarConexion("D", "F", 8);
        s.registrarConexion("E", "F", 2);

        
        assertOk(s.sucursalesParaTorneo("A", 11), 11,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E|F;Sucursal F");

        
        
        assertOk(s.sucursalesParaTorneo("A", 10), 9,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E");

        assertOk(s.sucursalesParaTorneo("A", 5), 5,
                "A;Sucursal A|C;Sucursal C|D;Sucursal D");
    }

    @Test
    public void testOk3() {
        
        
        assertOk(s.registrarConexion("A", "B", 2));
        assertOk(s.registrarConexion("A", "C", 6));
        assertOk(s.registrarConexion("B", "D", 5));
        assertOk(s.registrarConexion("C", "D", 8));








        
        assertOk(s.sucursalesParaTorneo("A", 22), 22,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E|F;Sucursal F|G;Sucursal G");













    }

    @Test
    public void testError1() {
        
        assertError1(s.sucursalesParaTorneo(null, 230));
        assertError1(s.sucursalesParaTorneo("", 230));
    }

    @Test
    public void testError2() {

        
        assertOk(s.registrarSucursal("COD", "Nom"));
        assertError2(s.sucursalesParaTorneo("COD2", 230));
    }

    @Test
    public void testError3() {

        
        assertOk(s.registrarSucursal("COD", "Nom"));
        assertError3(s.sucursalesParaTorneo("COD", 0));
        assertError3(s.sucursalesParaTorneo("COD", -20));
    }
}
