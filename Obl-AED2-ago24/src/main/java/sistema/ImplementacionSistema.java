package sistema;

import dominio.Sucursal;
import estructuras.ListaDoble;
import interfaz.*;

public class ImplementacionSistema implements Sistema {


    private static final int MAX_SUCURSALES = 3;

    private ListaDoble<Sucursal> listaSucursales; // estructura temporal hasta poder implementar graphs
    private int numSucursalesActuales;

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
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        return Retorno.noImplementada();
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
