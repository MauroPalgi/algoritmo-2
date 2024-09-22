package Estructuras;

public class NodoAB<T> {

    private T dato;
    private NodoAB<T> izq;
    private NodoAB<T> der;


    public NodoAB(T d, NodoAB<T> de, NodoAB<T> iz) {
        this.dato = d;
        this.izq = iz;
        this.der = de;
    }

    public NodoAB(T dato) {
        this.dato = dato;
    }

    public NodoAB() {
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoAB<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoAB<T> izq) {
        this.izq = izq;
    }

    public NodoAB<T> getDer() {
        return der;
    }

    public void setDer(NodoAB<T> der) {
        this.der = der;
    }
}