package estructuras;

public class ABBSucursal extends ABB<Sucursal>{
    // Método para obtener el vértice de mayor latencia
    public Sucursal obtenerVerticeMayorLatencia() {
        return obtenerVerticeMayorLatencia(this.getRaiz());
    }

    // Método auxiliar para recorrer el árbol y encontrar el vértice de mayor latencia
    private Sucursal obtenerVerticeMayorLatencia(NodoABB<Sucursal> nodo) {
        if (nodo == null) {
            return null; // Si el árbol está vacío
        }

        // Inicializamos la variable para el vértice de mayor latencia
        Sucursal sucursalMayorLatencia = nodo.getDato();

        // Buscamos en el subárbol izquierdo
        Sucursal mayorIzquierda = obtenerVerticeMayorLatencia(nodo.getIzq());
        if (mayorIzquierda != null && mayorIzquierda.getLatencia() > sucursalMayorLatencia.getLatencia()) {
            sucursalMayorLatencia = mayorIzquierda;
        }

        // Buscamos en el subárbol derecho
        Sucursal mayorDerecha = obtenerVerticeMayorLatencia(nodo.getDer());
        if (mayorDerecha != null && mayorDerecha.getLatencia() > sucursalMayorLatencia.getLatencia()) {
            sucursalMayorLatencia = mayorDerecha;
        }

        return sucursalMayorLatencia;
    }

}
