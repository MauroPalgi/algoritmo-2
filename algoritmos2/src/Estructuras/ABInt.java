package Estructuras;

public class ABInt {
    private NodoAB<Integer> raiz;

    public ABInt(NodoAB<Integer> raiz) {
        this.raiz = raiz;
    }

    public ABInt() {
    }

    public void setRaiz(NodoAB<Integer> raiz) {
        this.raiz = raiz;
    }

    public int cantNodos() {
        return cantNodosRec(raiz);
    }

    private int cantNodosRec(NodoAB<Integer> nodo) {
        if (nodo == null) {
            return 0;
        }

        return 1 + cantNodosRec(nodo.getIzq()) + cantNodosRec(nodo.getDer());
    }


    private int cantNodosRec1(NodoAB<Integer> nodo) {
        if (nodo.getIzq() == null && nodo.getDer() == null)
            return 1;

        int cant = 1;
        int cantIzq = 0;
        int cantDer = 0;
        if (nodo.getIzq() != null)
            cantIzq = cantNodosRec1(nodo.getIzq());
        if (nodo.getDer() != null)
            cantDer = cantNodosRec1(nodo.getDer());
        return cant + cantIzq + cantDer;
    }


    public int cantHojas() {
        return cantHojasRec(raiz);
    }

    private int cantHojasRec(NodoAB<Integer> nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.getIzq() == null && nodo.getDer() == null) {
            return 1;
        }
        return cantHojasRec(nodo.getDer()) + cantHojasRec(nodo.getIzq());
    }

    public boolean todosPares() {
        return todosPares(raiz);
    }

    private boolean todosPares(NodoAB<Integer> nodo) {
        if (nodo == null) {
            return true;
        }
        return nodo.getDato() % 2 == 0 && todosPares(nodo.getIzq()) && todosPares(nodo.getDer());
    }

    public boolean pertenece(int x) {
        return pertenece(x, raiz);
    }

    private boolean pertenece(int x, NodoAB<Integer> nodo) {

        if (nodo == null) {
            return false;
        }

        if (nodo.getDato() == x) {
            return true;
        } else {
            return pertenece(x, nodo.getIzq()) || pertenece(x, nodo.getDer());
        }
    }

}
