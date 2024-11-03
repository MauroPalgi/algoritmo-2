package sistema;

import dominio.Sucursal;
import dominio.Jugador;
import dominio.Equipo;
import estructuras.*;
import interfaz.*;
import utils.Resultado;
import utils.UTILS;

public class ImplementacionSistema implements Sistema {


    private static final int MAX_SUCURSALES = 3;
    // SUCURSAL
    private Grafo grafoRegiones;

    // JUGADOR
    private ABB<Jugador> abbJugadores = new ABB<>();
    private ABB<Jugador>  abbJugadoresEstandares = new ABB<>();
    private ABB<Jugador>  abbJugadoresPrincipiantes = new ABB<>();
    private ABB<Jugador>  abbJugadoresProfesionales = new ABB<>();;


    // EQUIPO
    private ABBEquipo abbEquipos = new ABBEquipo();

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= MAX_SUCURSALES || maxSucursales <= 0) {
            return Retorno.error1("NO SE PUEDO ESTAGBLECER EL MAXIMO DE SUCURSALES :" + maxSucursales + " , MAXIMO DE SUCURSALES :" + this.MAX_SUCURSALES);
        }
            this.grafoRegiones = new Grafo(maxSucursales, false); // grafo no dirigido

        System.out.println("max vertices grafo regiones: " + this.grafoRegiones.getMaxVertices());
            return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        System.out.println(alias);
        if (UTILS.esStringVacioONull(alias) ||
                UTILS.esStringVacioONull(nombre) ||
                UTILS.esStringVacioONull(apellido) ||
                categoria == null) {
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
        if (UTILS.esStringVacioONull(alias)) {
            return Retorno.error1("ALIAS ES NULL O VACIO");
        }
        Resultado<Jugador> jugadorBuscado = abbJugadores.buscarConIteracion(new Jugador(alias));
        if (jugadorBuscado.getDatoEncontrado() == null) {
            return Retorno.error2("NO EXISTE JUGADOR CON ALIAS" + alias);
        }
        return Retorno.ok(jugadorBuscado.getIteraciones(), jugadorBuscado.getDatoEncontrado().toString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        String result = inOrden(abbJugadores.getRaiz());
        if (result.isEmpty()) {
            return Retorno.ok("");
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
        if (UTILS.esStringVacioONull(nombreEquipo)|| UTILS.esStringVacioONull(aliasJugador)) {
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

        if (grafoRegiones.getMaxVertices() <= grafoRegiones.cantVertices()) {
            return Retorno.error1("Se alcanzó el límite de sucursales permitidas.");
        }

        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return Retorno.error2("Código o nombre son vacíos o null.");
        }

        Vertice verticeExistente = new Vertice(codigo);
        if (grafoRegiones.obtenerPos(verticeExistente) != -1) {
            return Retorno.error3("Ya existe una sucursal con ese código.");
        }

        Vertice nuevoVertice = new Vertice(codigo, nombre);
        grafoRegiones.agregarVertice(nuevoVertice);

        return Retorno.ok("Sucursal registrada exitosamente.");
    }


    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if (latencia < 0) {
            return Retorno.error1("La latencia no puede ser negativa.");
        }

        Vertice vertice1 = new Vertice(codigoSucursal1);
        Vertice vertice2 = new Vertice(codigoSucursal2);

        int posVertice1 = grafoRegiones.obtenerPos(vertice1);
        int posVertice2 = grafoRegiones.obtenerPos(vertice2);

        if (posVertice1 == -1 || posVertice2 == -1) {
            return Retorno.error3("Alguna de las sucursales no existe.");
        }

        Arista nuevaArista = new Arista(latencia);
        grafoRegiones.agregarArista(grafoRegiones.obtenerVertice(posVertice1), grafoRegiones.obtenerVertice(posVertice2), nuevaArista);

        return Retorno.ok("Conexión registrada exitosamente.");
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
