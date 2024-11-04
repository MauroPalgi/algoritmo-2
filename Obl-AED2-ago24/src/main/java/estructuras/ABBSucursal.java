package estructuras;

public class ABBSucursal extends ABB<Vertice>{
    // Método para obtener el vértice de mayor latencia
    public Vertice obtenerVerticeMayorLatencia() {
        return obtenerVerticeMayorLatencia(this.getRaiz());
    }

    // Método auxiliar para recorrer el árbol y encontrar el vértice de mayor latencia
    private Vertice obtenerVerticeMayorLatencia(NodoABB<Vertice> nodo) {
        if (nodo == null) {
            return null; // Si el árbol está vacío
        }

        // Inicializamos la variable para el vértice de mayor latencia
        Vertice verticeMayorLatencia = nodo.getDato();

        // Buscamos en el subárbol izquierdo
        Vertice mayorIzquierda = obtenerVerticeMayorLatencia(nodo.getIzq());
        if (mayorIzquierda != null && mayorIzquierda.getLatencia() > verticeMayorLatencia.getLatencia()) {
            verticeMayorLatencia = mayorIzquierda;
        }

        // Buscamos en el subárbol derecho
        Vertice mayorDerecha = obtenerVerticeMayorLatencia(nodo.getDer());
        if (mayorDerecha != null && mayorDerecha.getLatencia() > verticeMayorLatencia.getLatencia()) {
            verticeMayorLatencia = mayorDerecha;
        }

        return verticeMayorLatencia;
    }

}
