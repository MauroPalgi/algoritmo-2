package Estructuras;

public class NodoCola<T extends Comparable<T>> {

    private T dato;
    private NodoCola<T> siguiente;
    static private int idGlobal = 0;
    private final int idNodoCola;

    public NodoCola(T t) {
        this.dato = t;
        this.siguiente = null;
        this.idNodoCola = idGlobal;
        NodoCola.idGlobal++;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoCola<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola<T> siguiente) {
        this.siguiente = siguiente;
    }

    public static int getIdGlobal() {
        return idGlobal;
    }

    public static void setIdGlobal(int idGlobal) {
        NodoCola.idGlobal = idGlobal;
    }

    
    public String toString() {
        String text = "Nodo { dato = " + getDato().toString();
        if (getSiguiente() != null) {
            text += ", siguiente = {" + getSiguiente().getDato().toString() + " }";
        }
        text += " }\n";
        return text;
    }
}
