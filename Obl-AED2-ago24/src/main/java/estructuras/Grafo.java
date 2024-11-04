package estructuras;


import dominio.Sucursal;
import interfaz.ICola;
import interfaz.ILista;
import utils.UTILS;

import java.util.Arrays;

public class Grafo {
    private final Vertice[] vertices;
    private final Arista[][] aristas;
    private int cantVertices;
    private final int maxVertices;
    private final boolean dirigido;

    public Grafo(int cantMaxVertices, boolean esDirigido) {
        cantVertices = 0;
        maxVertices = cantMaxVertices;
        dirigido = esDirigido;

        vertices = new Vertice[maxVertices];
        aristas = new Arista[maxVertices][maxVertices];

        if (dirigido) {
            for (int i = 0; i < maxVertices; i++) {
                for (int j = 0; j < maxVertices; j++) {
                    aristas[i][j] = new Arista();
                }
            }
        } else {
            for (int i = 0; i < maxVertices; i++) {
                for (int j = 0; j < maxVertices; j++) {
                    Arista aux = new Arista();
                    aristas[i][j] = aux;
                    aristas[j][i] = aux;
                }
            }
        }
    }

    public Vertice[] getVertices() {
        return vertices;
    }

    public int getMaxVertices() {
        return this.maxVertices;
    }

    public int cantVertices() {
        return cantVertices;
    }


    public void agregarVertice(Vertice vert) {
        if (cantVertices < maxVertices) {
            int posLibre = obtenerPosLibre();
            vertices[posLibre] = vert;
            cantVertices++;
        }
    }

    public void borrarVertice(Vertice vert) {
        int posABorrar = obtenerPos(vert);

        for (int i = 0; i < aristas.length; i++) {
            aristas[posABorrar][i].setExiste(false);
            if (dirigido) {
                aristas[i][posABorrar].setExiste(false);
            }
        }
        vertices[posABorrar] = null;
        cantVertices--;
    }

    public void agregarArista(Vertice vInicial, Vertice vFinal, Arista arista) {
        int posVinicial = obtenerPos(vInicial);
        int posVfinal = obtenerPos(vFinal);

        Arista a = aristas[posVinicial][posVfinal];
        a.setPeso(arista.getPeso());
        a.setExiste(true);
    }

    public void borrarArista(Vertice vInicial, Vertice vFinal) {
        int posVinicial = obtenerPos(vInicial);
        int posVfinal = obtenerPos(vFinal);

        Arista a = aristas[posVinicial][posVfinal];
        a.setPeso(0);
        a.setExiste(false);
    }

    public Arista obtenerArista(Vertice vInicial, Vertice vFinal) {
        int posVinicial = obtenerPos(vInicial);
        int posVfinal = obtenerPos(vFinal);
        for (int i = 0; i < aristas.length; i++) {
            for (int j = 0; j < aristas[i].length; j++) {
                Arista a = aristas[i][j];
            }
        }
        return aristas[posVinicial][posVfinal];
    }

    public ILista<Vertice> adyacentes(Vertice vert) {
        ILista<Vertice> adyacentes = new Lista<>();
        int pos = obtenerPos(vert);

        for (int i = 0; i < aristas.length; i++) {
            if (aristas[pos][i].isExiste()) {
                adyacentes.insertar(vertices[i]);
            }
        }
        return adyacentes;
    }

    public void dfs(Vertice vert) {
        boolean[] visitados = new boolean[maxVertices];
        int posV = obtenerPos(vert);
        dfs(posV, visitados);
    }

    private void dfs(int posV, boolean[] visitados) {
        visitados[posV] = true;
        for (int i = 0; i < aristas.length; i++) {
            if (aristas[posV][i].isExiste() && !visitados[i]) {
                dfs(i, visitados);
            }
        }
    }

    public void bfs(Vertice vert) {
        int posV = obtenerPos(vert);
        boolean[] visitados = new boolean[maxVertices];
        ICola<Integer> cola = new Cola<>();
        cola.encolar(posV);
        visitados[posV] = true;
        while (!cola.estaVacia()) {
            int pos = cola.desencolar();
            System.out.print(vertices[pos] + " ");
            for (int i = 0; i < aristas.length; i++) {
                if (aristas[pos][i].isExiste() && !visitados[i]) {
                    visitados[i] = true;
                    cola.encolar(i);
                }
            }
        }
    }

    public boolean esPuntoCritico(Vertice vert) {
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
        int posSucursal = obtenerPos(vert); // Posición del vértice a verificar
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
        visitadosConExclusion[posSucursal] = true; // Excluir posSucursal
        this.dfs(posPrimerVisitado, visitadosConExclusion);
        for (int i = 0; i < visitadosOriginales.length; i++) {
            if (visitadosOriginales[i] != visitadosConExclusion[i]) {
                return true;
            }
        }
        return false;
    }

    public void dijkstra(Vertice vInicial) {
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
                    if (aristas[pos][j].isExiste() && !visitados[j]) {
                        int distanciaNueva = costos[pos] + aristas[pos][j].getPeso();
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


    public int obtenerPos(Vertice vert) {
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

    public Vertice obtenerVertice(int pos) {
        if (pos >= 0 && pos < vertices.length) {
            return vertices[pos];
        }
        return null;
    }

    public boolean existeArista(Vertice origen, Vertice destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        if (posOrigen == -1 || posDestino == -1) {
            return false;
        }

        // aca es para ver si es en ambas direcciones
        Arista aristaActual = aristas[posOrigen][posDestino];
        Arista aristaActualInvertida = aristas[posDestino][posOrigen];

        if (dirigido) {
            return aristaActual.isExiste();
        }
        return !dirigido && aristaActual.isExiste() && aristaActualInvertida.isExiste();
    }

    public void actualizarArista(Vertice origen, Vertice destino, int latencia) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        if (posOrigen != -1 && posDestino != -1) {
            aristas[posOrigen][posDestino].setPeso(latencia);
            if (!dirigido) {
                aristas[posDestino][posOrigen].setPeso(latencia);
            }
        }
    }

    public ABBSucursal obtenerABBSucursalesMenorLatencia(Vertice vInicial, int latenciaLimite) {
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
                    if (aristas[pos][j].isExiste() && !visitados[j]) {
                        int distanciaNueva = costos[pos] + aristas[pos][j].getPeso();
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
