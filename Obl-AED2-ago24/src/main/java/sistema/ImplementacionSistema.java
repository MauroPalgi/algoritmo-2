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
    private ABB<Jugador> abbJugadoresEstandares = new ABB<>();
    private ABB<Jugador> abbJugadoresPrincipiantes = new ABB<>();
    private ABB<Jugador> abbJugadoresProfesionales = new ABB<>();


    // EQUIPO
    private ABBEquipo abbEquipos = new ABBEquipo();

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= MAX_SUCURSALES || maxSucursales <= 0) {
            return Retorno.error1("NO SE PUEDO ESTABLECER EL MAXIMO DE SUCURSALES :" + maxSucursales + " , MAXIMO DE SUCURSALES :" + this.MAX_SUCURSALES);
        }
        System.out.println("Max Sucursales " + maxSucursales);
        System.out.println("grafo " + this.grafoRegiones);
        if (this.grafoRegiones != null) {
            reiniciarSistema();
        }
        this.grafoRegiones = new Grafo(maxSucursales, false); // grafo no dirigido
        return Retorno.ok();
    }

    private void reiniciarSistema() {
        grafoRegiones = null;
        abbJugadores = new ABB<>();
        abbJugadoresEstandares = new ABB<>();
        abbJugadoresPrincipiantes = new ABB<>();
        abbJugadoresProfesionales = new ABB<>();
        abbEquipos = new ABBEquipo();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {

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
        if (categoria == Categoria.ESTANDARD) {
            abbJugadoresEstandares.insertar(nuevoJugador);
        }
        if (categoria == Categoria.PRINCIPIANTE) {
            abbJugadoresPrincipiantes.insertar(nuevoJugador);
        }
        if (categoria == Categoria.PROFESIONAL) {
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
        if (unaCategoria == Categoria.PRINCIPIANTE) {
            raiz = abbJugadoresPrincipiantes.getRaiz();
        }
        if (unaCategoria == Categoria.PROFESIONAL) {
            raiz = abbJugadoresProfesionales.getRaiz();
        }

        String result = inOrden(raiz);
        if (result.isEmpty()) {
            return Retorno.ok("");
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
        if (nombreEquipo == null || nombreEquipo.isEmpty() || aliasJugador == null || aliasJugador.isEmpty()) {
            return Retorno.error1("ALGUN PARAMETRO ES NULL O VACIO");
        }
        Equipo equipoEncontrado = abbEquipos.buscar(new Equipo(nombreEquipo));
        if (equipoEncontrado == null) {
            return Retorno.error2("NO EXISTE EQUIPO CON NOMBRE " + nombreEquipo);
        }
        System.out.println(equipoEncontrado.toString());
        Jugador jugadorEncontrado = abbJugadores.buscar(new Jugador(aliasJugador));
        if (jugadorEncontrado == null) {
            return Retorno.error3("NO EXISTE JUGADOR CON ALIAS " + aliasJugador);
        }
        if (equipoEncontrado.getAbbIntegrantes().cantElementos() == 5) {
            return Retorno.error4("EL EQUIPO " + nombreEquipo + " YA TIENE 5 INTEGRANTES");
        }
        Jugador jugadorProfesional = abbJugadoresProfesionales.buscar(new Jugador(aliasJugador));
        System.out.println("here");
        if (jugadorProfesional == null) {
            return Retorno.error5("EL JUGADOR NO ES PROFESIONAL");
        }
        Jugador jugadorEnEquipo = abbEquipos.buscarJugadorEnEquipos(aliasJugador);
        System.out.println("jugador equipo " + jugadorEnEquipo);
        if (jugadorEnEquipo != null) {
            return Retorno.error6("EL JUGADOR : " + aliasJugador + " AL EQUIPO : " + jugadorEnEquipo.getNombreEquipo());
        }
        equipoEncontrado.getAbbIntegrantes().insertar(jugadorEncontrado);
        jugadorEncontrado.setNombreEquipo(equipoEncontrado.getNombre());
        return Retorno.ok();

    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if (nombreEquipo == null || nombreEquipo.isEmpty()) {
            return Retorno.error1("NOMBRE DE QUIPO ES NULL O VACIO");
        }
        System.out.println("nombre equipo" + nombreEquipo);
        abbEquipos.listarAscendente();
        Equipo equipoBuscado = abbEquipos.buscar(new Equipo(nombreEquipo));

        if (equipoBuscado == null) {
            return Retorno.error2("NO EXISTE EQUIPO CON ESE NOMBRE");

        }

        String result = inOrden(equipoBuscado.getAbbIntegrantes().getRaiz());
        if (result.isEmpty()) {
            return Retorno.ok("");
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

        if (codigoSucursal1 == null || codigoSucursal1.isEmpty() || codigoSucursal2 == null || codigoSucursal2.isEmpty()) {
            return Retorno.error2("Alguno de los parámetros de código de sucursal es vacío o null.");
        }

        Vertice vertice1 = new Vertice(codigoSucursal1);
        Vertice vertice2 = new Vertice(codigoSucursal2);

        int posVertice1 = grafoRegiones.obtenerPos(vertice1);
        int posVertice2 = grafoRegiones.obtenerPos(vertice2);

        if (posVertice1 == -1 || posVertice2 == -1) {
            return Retorno.error3("Alguna de las sucursales no existe.");
        }
        if (grafoRegiones.existeArista(vertice1, vertice2)) {
            return Retorno.error4("Ya existe una conexión entre las dos sucursales.");
        }

        Arista nuevaArista = new Arista(latencia);
        Vertice sucursal1 = grafoRegiones.obtenerVertice(posVertice1);
        Vertice sucursal2 = grafoRegiones.obtenerVertice(posVertice2);
        grafoRegiones.agregarArista(sucursal1, sucursal2, nuevaArista);

        grafoRegiones.agregarArista(sucursal2, sucursal1, nuevaArista);
        return Retorno.ok("Conexión registrada exitosamente.");
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if (latencia <= 0) {
            return Retorno.error1("La latencia no puede ser negativa.");
        }

        if (codigoSucursal1 == null || codigoSucursal1.isEmpty() || codigoSucursal2 == null || codigoSucursal2.isEmpty()) {
            return Retorno.error2("Alguno de los parámetros de código de sucursal es vacío o null.");
        }

        Vertice sucursalOrigen = new Vertice(codigoSucursal1);
        Vertice sucursalDestino = new Vertice(codigoSucursal2);

        if (grafoRegiones.obtenerPos(sucursalOrigen) == -1 || grafoRegiones.obtenerPos(sucursalDestino) == -1) {
            return Retorno.error3("Una o ambos codigo de sucursal no existen en el Actual Grafo de Regiones.");
        }

        if (!grafoRegiones.existeArista(sucursalOrigen, sucursalDestino)) {
            return Retorno.error4("No existe la conexion entre Sucursales.");
        }
        grafoRegiones.actualizarArista(sucursalOrigen, sucursalDestino, latencia);
        return Retorno.ok("Conexión actualizada exitosamente.");
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        if (codigoSucursal == null || codigoSucursal.isEmpty()) {
            return Retorno.error1("Codigo es vacío o null.");
        }
        int posSucursal = grafoRegiones.obtenerPos(new Vertice(codigoSucursal));
        if (posSucursal == -1) {
            return Retorno.error2("La sucursal no existe.");
        }
        Vertice sucursal = grafoRegiones.obtenerVertice(posSucursal);
        boolean esPuntoCritico = grafoRegiones.esPuntoCritico(sucursal);
        return Retorno.ok(esPuntoCritico ? "SI" : "NO");
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        if (codigoSucursalAnfitriona == null || codigoSucursalAnfitriona.isEmpty()) {
            return Retorno.error1("Codigo Sucursal anfitriona es vacío o null.");
        }


        int posSucursal = grafoRegiones.obtenerPos(new Vertice(codigoSucursalAnfitriona));
        if (posSucursal == -1) {
            return Retorno.error2("No existe el código de la sucursal anfitriona.");
        }

        if (latenciaLimite <= 0) {
            return Retorno.error3("La latencia no puede ser negativa.");
        }
        Vertice sucursal = grafoRegiones.obtenerVertice(posSucursal);
        ABBSucursal sucursalesMenorLatencia = (ABBSucursal) grafoRegiones.obtenerABBSucursalesMenorLatencia(sucursal, latenciaLimite);
        String sucursalesString = sucursalesMenorLatencia.listarAscendenteString();
        Vertice sucursalMayorLatencia = sucursalesMenorLatencia.obtenerVerticeMayorLatencia();
        return Retorno.ok(sucursalMayorLatencia.getLatencia(), sucursalesString);
    }
}
