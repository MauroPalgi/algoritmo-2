package sistema;

import dominio.Sucursal;
import dominio.Jugador;
import estructuras.ABBJugador;
import estructuras.ListaDoble;
import estructuras.NodoABB;
import interfaz.*;
import utils.UTILS;

public class ImplementacionSistema implements Sistema {


    private static final int MAX_SUCURSALES = 3;
    // SUCURSAL
    private ListaDoble<Sucursal> listaSucursales; // estructura temporal hasta poder implementar graphs
    private int numSucursalesActuales;

    // JUGADOR
    private ABBJugador abbJugadores;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= MAX_SUCURSALES || maxSucursales <= 0) {
            return Retorno.error1("NO SE PUEDO ESTAGBLECER EL MAXIMO DE SUCURSALES :" + maxSucursales + " , MAXIMO DE SUCURSALES :" + this.MAX_SUCURSALES);
        } else {
            numSucursalesActuales = maxSucursales;
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if (UTILS.validarStringParametros(alias, nombre, apellido, categoria.getTexto())) {
            return Retorno.error1("ALGUN PARAMETRO ES NULL O VACIO");
        }
        if (abbJugadores.pertenece(new Jugador(alias))) {
            return Retorno.error2("EL JUGADOR CON ALIAS " + alias + ", YA EXISTE");
        }
        Jugador nuevoJugador = new Jugador(alias, nombre, apellido, categoria);
        abbJugadores.insertar(nuevoJugador);
        return Retorno.ok("JUGADOR INSERTADO EXITOSAMENTE");
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if (UTILS.validarStringParametros(alias)) {
            return Retorno.error1("ALIAS ES NULL O VACIO");
        }
        Jugador jugadorBuscado = abbJugadores.buscar(new Jugador(alias));
        if (jugadorBuscado == null) {
            return Retorno.error2("NO EXISTE JUGADOR CON ALIAS" + alias);
        }
        return Retorno.ok(0, jugadorBuscado.toString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }
}
