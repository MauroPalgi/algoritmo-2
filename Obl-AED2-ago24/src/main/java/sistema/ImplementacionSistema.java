package sistema;

import dominio.Sucursal;
import dominio.Jugador;
import dominio.Equipo;
import estructuras.*;
import interfaz.*;
import utils.UTILS;

public class ImplementacionSistema implements Sistema {


    private static final int MAX_SUCURSALES = 3;
    // SUCURSAL
    private ListaDoble<Sucursal> listaSucursales; // estructura temporal hasta poder implementar graphs
    private int numSucursalesActuales;

    // JUGADOR

    private ABB<Jugador> abbJugadores;
    private ABB<Jugador>  abbJugadoresEstandares;
    private ABB<Jugador>  abbJugadoresPrincipiantes;
    private ABB<Jugador>  abbJugadoresProfesionales;


    // EQUIPO
    private ABBEquipo abbEquipos;

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
        if (categoria == Categoria.ESTANDARD){
            abbJugadoresEstandares.insertar(nuevoJugador);
        }
        if (categoria == Categoria.PRINCIPIANTE){
            abbJugadoresPrincipiantes.insertar(nuevoJugador);
        }
        if (categoria == Categoria.PROFESIONAL){
            abbJugadoresProfesionales.insertar(nuevoJugador);
        }
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
        String result = inOrden(abbJugadores.getRaiz());
        if (result.isEmpty()) {
            return Retorno.ok();
        }
        return Retorno.ok(result.substring(0, result.length() - 1));
    }

    public String inOrden(NodoABB nodo) {
        if (nodo == null) {
            return "";
        }
        String left = inOrden(nodo.getIzq());
        String current = nodo.getDato().toString() + "|";
        String right = inOrden(nodo.getDer());
        return left + current + right;
    }


    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        NodoABB raiz = abbJugadoresEstandares.getRaiz();
        if (unaCategoria == Categoria.PRINCIPIANTE){
            raiz = abbJugadoresPrincipiantes.getRaiz();
        }
        if (unaCategoria == Categoria.PROFESIONAL){
            raiz = abbJugadoresProfesionales.getRaiz();
        }

        String result = inOrden(raiz);
        if (result.isEmpty()) {
            return Retorno.ok();
        }
        // Remove the last "|" character from the result string, if it exists
        return Retorno.ok(result.substring(0, Math.max(0, result.length() - 1)));
    }

    public String inOrdenFiltrado(NodoABB<Jugador> nodo, Categoria categoria) {
        if (nodo == null) {
            return "";
        }
        String left = inOrdenFiltrado(nodo.getIzq(), categoria);
        String current = "";
        if (nodo.getDato().getCategoria().equals(categoria)) {
            current = nodo.getDato().toString() + "|";
        }
        String right = inOrdenFiltrado(nodo.getDer(), categoria);
        return left + current + right;
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        if (nombre == null || nombre.isEmpty() || manager == null || manager.isEmpty()) {
            return Retorno.error1("Nombre o manager no pueden ser nulos o vacíos.");
        }
        if (abbEquipos.pertenece(new Equipo(nombre, manager))) {
            return Retorno.error2("Ya existe un equipo con ese nombre.");
        }
        Equipo nuevoEquipo = new Equipo(nombre, manager);
        abbEquipos.insertar(nuevoEquipo);
        return Retorno.ok();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        if (UTILS.validarStringParametros(nombreEquipo, aliasJugador)) {
            return Retorno.error1("ALGUN PARAMETRO ES NULL O VACIO");
        }
        Equipo equipoEncontrado = abbEquipos.buscar(new Equipo(nombreEquipo));
        if (equipoEncontrado == null) {
            return Retorno.error2("NO EXISTE EQUIPO CON NOMBRE " + nombreEquipo);
        }
        Jugador jugadorEncontrado = abbJugadores.buscar(new Jugador(aliasJugador));
        if (jugadorEncontrado == null) {
            return Retorno.error3("NO EXISTE JUGADOR CON ALIAS " + aliasJugador);
        }
        if (equipoEncontrado.getAbbIntegrantes().cantElementos() == 5){
            return Retorno.error4("EL EQUIPO " + nombreEquipo + " YA TIENE 5 INTEGRANTES");
        }
        Jugador jugadorEnEquipo = abbEquipos.buscarJugadorEnEquipos(aliasJugador);
        if (jugadorEnEquipo != null){
            return Retorno.error6("EL JUGADOR : " + aliasJugador + " AL EQUIPO : " + jugadorEnEquipo.getNombreEquipo());
        }
        equipoEncontrado.getAbbIntegrantes().insertar(jugadorEncontrado);
        jugadorEncontrado.setNombreEquipo(equipoEncontrado.getNombre());
        return Retorno.ok();

    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if (UTILS.validarStringParametros(nombreEquipo)) {
            return Retorno.error1("NOMBRE DE QUIPO ES NULL O VACIO");
        }
        Equipo equipoBuscado = abbEquipos.buscar(new Equipo(nombreEquipo));
        if(equipoBuscado == null) {
            return Retorno.error2("NO EXISTE EQUIPO CON ESE NOMBRE");

        }

        String result = inOrden(equipoBuscado.getAbbIntegrantes().getRaiz());
        if (result.isEmpty()) {
            return Retorno.ok();
        }

        return Retorno.ok(result.substring(0, result.length() - 1));
    }

    @Override
    public Retorno listarEquiposDescendente() {
        String result = listarEquiposDescendente(abbEquipos.getRaiz());
        if (result.isEmpty()) {
            return Retorno.ok();
        }
        return Retorno.ok(result.substring(0, result.length() - 1));
    }

    private String listarEquiposDescendente(NodoABB<Equipo> nodo) {
        if (nodo == null) {
            return "";
        }
        String right = listarEquiposDescendente(nodo.getDer());
        String current = nodo.getDato().getNombre() + ";" + nodo.getDato().getManager() + ";" + nodo.getDato().getAbbIntegrantes().cantElementos() + "|";
        String left = listarEquiposDescendente(nodo.getIzq());
        return right + current + left;
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
