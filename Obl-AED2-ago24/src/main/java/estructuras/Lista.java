package estructuras;

import interfaz.ILista;

import java.util.Iterator;

public class Lista<T> implements ILista<T> {

    private NodoLista<T> inicio;
    protected int largo;

    public Lista() {
        this.inicio = null;
        this.largo = 0;
    }

    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }

    public void borrar(T dato) {

    }


    public int largo() {
        return largo;
    }


    public boolean existe(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }


    public T recuperar(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return aux.getDato();
            }
            aux = aux.getSig();
        }
        return null;
    }


    public boolean esVacia() {
        return largo == 0;
    }


    public boolean esLlena() {
        return false;
    }


    public void imprimirDatos() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getSig() != null){
                System.out.print(aux.getDato() + " -> ");
            }else{
                System.out.print(aux.getDato());
            }
            aux = aux.getSig();
        }
    }








    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.dato;
                aux = aux.sig;
                return dato;
            }

            @Override
            public void remove() {
            }

        };
    }


    private class NodoLista<T>{
        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }


}
