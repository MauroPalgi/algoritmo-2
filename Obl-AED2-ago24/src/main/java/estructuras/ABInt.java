package estructuras;

public class ABInt extends AB<Integer> {
    private NodoAB<Integer> raiz;


    public ABInt(NodoAB<Integer> raiz) {
        super(raiz);
    }

    /**
     * Práctico 1 - Ejercicio 2 - Parte D
     * Retorna true si y solo si todos los elementos del AB son pares
     */
    public boolean todosPares() {
        return todosPares(raiz);
    }

    private boolean todosPares(NodoAB<Integer> nodo) {
        if (nodo == null)
            return true;
        return (nodo.getDato() % 2 == 0) && todosPares(nodo.getIzq()) && todosPares(nodo.getDer());
    }

}
