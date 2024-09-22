package estructuras;

public class AB<T> {
    protected NodoAB<T> raiz;

    public AB(NodoAB<T> raiz) {
        this.raiz = raiz;
    }

    /**
     * Práctico 1 - Ejercicio 2 - Parte A
     * Retorna la cantidad de nodos del AB
     */
    public int cantNodos() {
        return cantNodosRec(raiz);
    }

    private int cantNodosRec(NodoAB<T> nodo) {
        if (nodo == null)
            return 0;
        return 1 + cantNodosRec(nodo.getIzq()) + cantNodosRec(nodo.getDer());
    }

    /**
     * Práctico 1 - Ejercicio 2 - Parte B
     * Retorna la cantidad de nodos hoja del AB
     */
    public int cantHojas() {
        return cantHojasRec(raiz);
    }

    private int cantHojasRec(NodoAB<T> nodo) {
        if (nodo == null)
            return 0;
        if (nodo.getIzq() == null && nodo.getDer() == null) {
            // System.out.println(nodo.getDato());
            return 1;
        }
        return cantHojasRec(nodo.getIzq()) + cantHojasRec(nodo.getDer());
    }

    /**
     * Práctico 1 - Ejercicio 2 - Parte C
     * Retorna la altura del árbol
     */
    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoAB<T> nodo) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.getIzq() == null && nodo.getDer() == null) {
            return 0;
        }

        int altIzq = altura(nodo.getIzq());
        int altDer = altura(nodo.getDer());
        int altMasAlta = Math.max(altIzq, altDer);
        return 1 + altMasAlta;
    }

    /**
     * Práctico 1 - Ejercicio 2 - Parte G
     * Retorna true si y solo si el dato pasado como parámetro pertenece al AB
     */
    public boolean pertenece(T x) {
        return pertenece(x, raiz);
    }

    private boolean pertenece(T x, NodoAB<T> nodo) {
        if (nodo == null) {
            return false;
        }

        if (nodo.getDato().equals(x)) {
            return true;
        } else {
            return pertenece(x, nodo.getIzq()) || pertenece(x, nodo.getDer());
        }
        // return (nodo.getDato() == x) || pertenece(x, nodo.getIzq()) || pertenece(x, nodo.getDer());
    }


}
