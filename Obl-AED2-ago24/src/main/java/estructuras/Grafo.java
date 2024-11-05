package estructuras;


import interfaz.ICola;
import interfaz.ILista;

public class Grafo {
    protected Sucursal[] vertices;
    protected Conexion[][] conexions;
    protected int cantVertices;
    protected int maxVertices;
    protected boolean dirigido;

    public Grafo(int cantMaxVertices, boolean esDirigido) {
        cantVertices = 0;
        maxVertices = cantMaxVertices;
        dirigido = esDirigido;

        vertices = new Sucursal[maxVertices];
        conexions = new Conexion[maxVertices][maxVertices];

        if (dirigido) {
            for (int i = 0; i < maxVertices; i++) {
                for (int j = 0; j < maxVertices; j++) {
                    conexions[i][j] = new Conexion();
                }
            }
        } else {
            for (int i = 0; i < maxVertices; i++) {
                for (int j = 0; j < maxVertices; j++) {
                    Conexion aux = new Conexion();
                    conexions[i][j] = aux;
                    conexions[j][i] = aux;
                }
            }
        }
    }

    public Sucursal[] getVertices() {
        return vertices;
    }

    public int getMaxVertices() {
        return this.maxVertices;
    }

    public int cantVertices() {
        return cantVertices;
    }


    public void agregarVertice(Sucursal vert) {
        if (cantVertices < maxVertices) {
            int posLibre = obtenerPosLibre();
            vertices[posLibre] = vert;
            cantVertices++;
        }
    }

    public void borrarVertice(Sucursal vert) {
        int posABorrar = obtenerPos(vert);

        for (int i = 0; i < conexions.length; i++) {
            conexions[posABorrar][i].setExiste(false);
            if (dirigido) {
                conexions[i][posABorrar].setExiste(false);
            }
        }
        vertices[posABorrar] = null;
        cantVertices--;
    }

    public void agregarArista(Sucursal vInicial, Sucursal vFinal, Conexion conexion) {
        int posVinicial = obtenerPos(vInicial);
        int posVfinal = obtenerPos(vFinal);

        Conexion a = conexions[posVinicial][posVfinal];
        a.setPeso(conexion.getPeso());
        a.setExiste(true);
    }

    public void borrarArista(Sucursal vInicial, Sucursal vFinal) {
        int posVinicial = obtenerPos(vInicial);
        int posVfinal = obtenerPos(vFinal);

        Conexion a = conexions[posVinicial][posVfinal];
        a.setPeso(0);
        a.setExiste(false);
    }

    public Conexion obtenerArista(Sucursal vInicial, Sucursal vFinal) {
        int posVinicial = obtenerPos(vInicial);
        int posVfinal = obtenerPos(vFinal);
        for (int i = 0; i < conexions.length; i++) {
            for (int j = 0; j < conexions[i].length; j++) {
                Conexion a = conexions[i][j];
            }
        }
        return conexions[posVinicial][posVfinal];
    }

    public ILista<Sucursal> adyacentes(Sucursal vert) {
        ILista<Sucursal> adyacentes = new Lista<>();
        int pos = obtenerPos(vert);

        for (int i = 0; i < conexions.length; i++) {
            if (conexions[pos][i].isExiste()) {
                adyacentes.insertar(vertices[i]);
            }
        }
        return adyacentes;
    }

    public void dfs(Sucursal vert) {
        boolean[] visitados = new boolean[maxVertices];
        int posV = obtenerPos(vert);
        dfs(posV, visitados);
    }

    private void dfs(int posV, boolean[] visitados) {
        visitados[posV] = true;
        for (int i = 0; i < conexions.length; i++) {
            if (conexions[posV][i].isExiste() && !visitados[i]) {
                dfs(i, visitados);
            }
        }
    }

    public void bfs(Sucursal vert) {
        int posV = obtenerPos(vert);
        boolean[] visitados = new boolean[maxVertices];
        ICola<Integer> cola = new Cola<>();
        cola.encolar(posV);
        visitados[posV] = true;
        while (!cola.estaVacia()) {
            int pos = cola.desencolar();
            System.out.print(vertices[pos] + " ");
            for (int i = 0; i < conexions.length; i++) {
                if (conexions[pos][i].isExiste() && !visitados[i]) {
                    visitados[i] = true;
                    cola.encolar(i);
                }
            }
        }
    }

    public boolean esPuntoCritico(Sucursal vert) {
        /*
        - Obtener la posicion del vertice vert.
        - Ejecutar dfs (el metodo privado), pasando un array de visitados y la posicion de vert,
            luego me quedo con el array de visitados que pasamos por parámetro para comparar luego.

        - Tengo que buscar el primer true del array de visitados anterior y me quedo con dicha posicion
            para usar como vertice de inicio para el próximo dfs.

        - Ejecutar nuevamente dfs, pero pasando un array de visitados en donde la posicion de vert tenga true.

        - Comparo el array de visitado de ambas ejecuciones de dfs, si hay diferencias devuelvo true,
            ya que el vertice vert es un punto crítico.
         */
        int posSucursal = obtenerPos(vert); 
        int posPrimerVisitado = -1;
        boolean[] visitadosOriginales = new boolean[maxVertices];
        this.dfs(posSucursal, visitadosOriginales);
        for (int i = 0; i < visitadosOriginales.length; i++) {
            if (visitadosOriginales[i] && i != posSucursal) {
                posPrimerVisitado = i;
                break;
            }
        }
        if (posPrimerVisitado == -1) {
            return false;
        }
        boolean[] visitadosConExclusion = new boolean[maxVertices];
        visitadosConExclusion[posSucursal] = true; 
        this.dfs(posPrimerVisitado, visitadosConExclusion);
        for (int i = 0; i < visitadosOriginales.length; i++) {
            if (visitadosOriginales[i] != visitadosConExclusion[i]) {
                return true;
            }
        }
        return false;
    }

    public void dijkstra(Sucursal vInicial) {
        boolean[] visitados = new boolean[maxVertices];
        int[] costos = new int[maxVertices];
        int[] vengo = new int[maxVertices];

        for (int i = 0; i < maxVertices; i++) {
            costos[i] = Integer.MAX_VALUE;
            vengo[i] = -1;
            visitados[i] = false;
        }

        int posOrigen = obtenerPos(vInicial);

        costos[posOrigen] = 0;

        for (int i = 0; i < cantVertices; i++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorCosto(costos, visitados);

            if (pos != -1) {
                visitados[pos] = true;

                for (int j = 0; j < maxVertices; j++) {
                    if (conexions[pos][j].isExiste() && !visitados[j]) {
                        int distanciaNueva = costos[pos] + conexions[pos][j].getPeso();
                        if (costos[j] > distanciaNueva) {
                            costos[j] = distanciaNueva;
                            vengo[j] = pos;
                        }
                    }
                }
            }
        }
    }

    public int obtenerSiguienteVerticeNoVisitadoDeMenorCosto(int[] costos, boolean[] visitados) {
        int posMin = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < maxVertices; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }


    public int obtenerPos(Sucursal vert) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equals(vert)) {
                return i;
            }
        }
        return -1;
    }

    public int obtenerPosLibre() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public Sucursal obtenerVertice(int pos) {
        if (pos >= 0 && pos < vertices.length) {
            return vertices[pos];
        }
        return null;
    }

    public boolean existeArista(Sucursal origen, Sucursal destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        if (posOrigen == -1 || posDestino == -1) {
            return false;
        }

        
        Conexion conexionActual = conexions[posOrigen][posDestino];
        Conexion conexionActualInvertida = conexions[posDestino][posOrigen];

        if (dirigido) {
            return conexionActual.isExiste();
        }
        return !dirigido && conexionActual.isExiste() && conexionActualInvertida.isExiste();
    }

    public void actualizarArista(Sucursal origen, Sucursal destino, int latencia) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        if (posOrigen != -1 && posDestino != -1) {
            conexions[posOrigen][posDestino].setPeso(latencia);
            if (!dirigido) {
                conexions[posDestino][posOrigen].setPeso(latencia);
            }
        }
    }

    public ABBSucursal obtenerABBSucursalesMenorLatencia(Sucursal vInicial, int latenciaLimite) {
        boolean[] visitados = new boolean[maxVertices];
        int[] costos = new int[maxVertices];
        int[] vengo = new int[maxVertices];
        ABBSucursal sucursales = new ABBSucursal();

        for (int i = 0; i < maxVertices; i++) {
            costos[i] = Integer.MAX_VALUE;
            vengo[i] = -1;
            visitados[i] = false;
        }

        int posOrigen = obtenerPos(vInicial);
        costos[posOrigen] = 0;
        vertices[posOrigen].setLatencia(0);
        sucursales.insertar(vertices[posOrigen]);
        for (int i = 0; i < cantVertices; i++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorCosto(costos, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < maxVertices; j++) {
                    if (conexions[pos][j].isExiste() && !visitados[j]) {
                        int distanciaNueva = costos[pos] + conexions[pos][j].getPeso();
                        if (distanciaNueva <= latenciaLimite && costos[j] > distanciaNueva) {
                            vertices[j].setLatencia(distanciaNueva);
                            sucursales.insertar(vertices[j]);
                            costos[j] = distanciaNueva;
                            vengo[j] = pos;
                        }
                    }
                }
            }
        }
        sucursales.listarAscendente();
        return sucursales;
    }

}
