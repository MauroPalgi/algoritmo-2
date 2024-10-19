package estructuras;


import utils.Resultado;

public class ABB<T extends Comparable<T>> {

    private NodoABB<T> raiz;

    public NodoABB<T> getRaiz() {
        return raiz;
    }

    /**
     * Práctico 1 - Ejercicio 5 - Parte A
     * Inserta el dato pasado como parámetro en el árbol manteniéndolo ordenado.
     */
    public void insertar(T dato) {
        if (raiz == null)
            raiz = new NodoABB<>(dato);
        else
            insertar(raiz, dato);
    }

    private void insertar(NodoABB<T> nodo, T dato) {
        if (nodo.getDato().compareTo(dato) > 0) {
            if (nodo.getIzq() == null)
                nodo.setIzq(new NodoABB<>(dato));
            else
                insertar(nodo.getIzq(), dato);
        } else if (nodo.getDato().compareTo(dato) < 0) {
            if (nodo.getDer() == null)
                nodo.setDer(new NodoABB<>(dato));
            else
                insertar(nodo.getDer(), dato);
        }
    }

    /**
     * Práctico 1 - Ejercicio 5 - Parte B
     * Retorna true sii el dato pasado como parámetro pertenece al ABB.
     */
    public boolean pertenece(T dato) {
        return pertenece(raiz, dato);
    }

    private boolean pertenece(NodoABB<T> nodo, T dato) {
        if (nodo != null) {
            if (nodo.getDato().equals(dato)) {
                return true;
            } else if (nodo.getDato().compareTo(dato) > 0) {
                return pertenece(nodo.getIzq(), dato);
            } else if (nodo.getDato().compareTo(dato) < 0) {
                return pertenece(nodo.getDer(), dato);
            }
        }
        return false;
    }


    public T buscar(T dato) {
        return buscar(raiz, dato);
    }

    private T buscar(NodoABB<T> nodo, T dato) {
        if (nodo != null) {
        System.out.println(dato.toString());
            if (nodo.getDato().compareTo(dato) == 0) {
                return nodo.getDato();
            } else if (nodo.getDato().compareTo(dato) > 0) {
                return buscar(nodo.getIzq(), dato);
            } else if (nodo.getDato().compareTo(dato) < 0) {
                return buscar(nodo.getDer(), dato);
            }
        }
        return null;
    }

    public Resultado<T> buscarConIteracion(T dato) {
        return buscarConIteracion(raiz, dato, 0);  // Inicializa las iteraciones en 0
    }

    private Resultado<T> buscarConIteracion(NodoABB<T> nodo, T dato, int iteraciones) {
        if (nodo != null) {
            iteraciones++;  // Cada vez que se hace una comparación, aumentamos el contador
            System.out.println("Iteración " + iteraciones + ": Buscando " + dato.toString());

            if (nodo.getDato().compareTo(dato) == 0) {
                return new Resultado<>(nodo.getDato(), iteraciones);  // Nodo encontrado
            } else if (nodo.getDato().compareTo(dato) > 0) {
                return buscarConIteracion(nodo.getIzq(), dato, iteraciones);  // Buscar en el subárbol izquierdo
            } else {
                return buscarConIteracion(nodo.getDer(), dato, iteraciones);  // Buscar en el subárbol derecho
            }
        }
        return new Resultado<>(null, iteraciones);  // Si no se encuentra, devuelve null y las iteraciones
    }

    /**
     * Práctico 1 - Ejercicio 5 - Parte C
     * Lista en pantalla los elementos del ABB ordenados de menor a mayor.
     */
    public void listarAscendente() {
        listarAscendente(raiz);
    }

    private void listarAscendente(NodoABB<T> nodo) {
        if (nodo != null) {
            listarAscendente(nodo.getIzq());
            System.out.println(" " + nodo.getDato() + " ");
            listarAscendente(nodo.getDer());
        }
    }

    /**
     * Práctico 1 - Ejercicio 5 - Parte D
     * Lista en pantalla los elementos del ABB ordenados de mayor a menor.
     */
    public void listarDescendente() {
        listarDescendente(raiz);
    }

    private void listarDescendente(NodoABB<T> nodo) {
        if (nodo != null) {
            listarDescendente(nodo.getDer());
            System.out.println(" " + nodo.getDato() + " ");
            listarDescendente(nodo.getIzq());
        }
    }

    public String listarDescendenteString() {
        return listarDescendenteString(raiz);
    }

    private String listarDescendenteString(NodoABB<T> nodo) {
        if (nodo != null) {
            return listarDescendenteString(nodo.getDer()) + "|" + nodo.getDato() + "|" + listarDescendenteString(nodo.getIzq());
        }
        return "";
    }

    public int cantElementos(){
        return cantElementos(this.raiz);
    }

    private int cantElementos(NodoABB<T> nodo) {
        if (nodo == null){
            return 0;
        }
        return 1 + cantElementos(nodo.getDer()) + cantElementos(nodo.getIzq());
    }
}
