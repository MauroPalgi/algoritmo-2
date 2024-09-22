package Estructuras;


public class ABB<T extends Comparable<T>> {

    private NodoABB<T> raiz;

    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new NodoABB<>(dato);
        } else {
            insertar(raiz, dato);
        }
    }

    private void insertar(NodoABB<T> nodo, T dato) {
        if (nodo.dato.compareTo(dato) > 0) { // el dato es mayor
            if (nodo.izq == null) {
                nodo.izq = new NodoABB<>(dato);
            } else {
                insertar(nodo.izq, dato);
            }
        } else if ((nodo.dato.compareTo(dato) < 0)) {
            if (nodo.der == null) {
                nodo.der = new NodoABB<>(dato);
            } else {
                insertar(nodo.der, dato);
            }
        }
    }


    private class NodoABB<Q extends Comparable<Q>> {
        private Q dato;
        private NodoABB<Q> izq;
        private NodoABB<Q> der;

        public NodoABB(Q dato) {
            this.dato = dato;
        }
    }
}
