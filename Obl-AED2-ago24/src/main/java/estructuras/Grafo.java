package estructuras;

import interfaz.ICola;
import estructuras.ILista;
import estructuras.Lista;

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
        System.out.print(vertices[posV] + " ");
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
        while(!cola.estaVacia()){
            int pos = cola.desencolar();
            System.out.print(vertices[pos] + " ");
            for (int i = 0; i < aristas.length; i++) {
                if(aristas[pos][i].isExiste() && !visitados[i]){
                    visitados[i]=true;
                    cola.encolar(i);
                }
            }
        }
    }

    public boolean esPuntoCritico(Vertice vert){
        /*
        - Obtener la posicion del vertice vert.
        - Ejecutar dfs (el metodo privado), pasando un array de visitados y la posicion de vert,
            luego me quedo con el array de visitados que pasamos por parámetro para comparar luego.

        - Hacer una copia de la matriz de aristas y quitarle todas las aristas asociadas a vert.

        - Tengo que buscar el primer true del array de visitados anterior y me quedo con dicha posicion
            para usar como vertice de inicio para el próximo dfs
        - Ejecutar dfs, pero utilizando la copia de la matriz de aristas y me quedo con su array de visitados

        - Comparo el array de visitado de ambas ejecuciones de dfs teniendo en cuenta el no comparar la posicion
            del vertice vert.

        - Si hay diferencias devuelvo true, ya que el vertice vert es un punto crítico.
         */

        return false;
    }

    private int obtenerPos(Vertice vert) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equals(vert)) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPosLibre() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }


}
