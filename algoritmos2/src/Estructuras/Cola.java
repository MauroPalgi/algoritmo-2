package Estructuras;

public class Cola<T extends Comparable<T>> {

    private NodoCola<T> inicio = null;
    private NodoCola<T> fin = null;
    private int cantidad = 0;

    public boolean esVacia() {
        return cantidad == 0;
    }

    public void agregarInicio(T data) {
        NodoCola<T> nuevo = new NodoCola<>(data);
        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        cantidad++;
    }

    public void agregarFinal(T data) {
        NodoCola<T> nuevo = new NodoCola<>(data);
        if (fin == null) {
            inicio = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        cantidad++;
    }

    public void borrarInicio() {
        if (inicio == null) return;
        inicio = inicio.getSiguiente();
        if (inicio == null) fin = null;
        cantidad--;
    }

    public void borrarFin() {
        if (inicio == null) return;
        if (inicio == fin) {
            inicio = fin = null;
        } else {
            NodoCola<T> current = inicio;
            while (current.getSiguiente() != fin) {
                current = current.getSiguiente();
            }
            current.setSiguiente(null);
            fin = current;
        }
        cantidad--;
    }

    public void borrarElemento(T data) {
        if (inicio == null) return;

        if (inicio.getDato().compareTo(data) == 0) {
            borrarInicio();
            return;
        }

        NodoCola<T> current = inicio;
        while (current.getSiguiente() != null) {
            if (current.getSiguiente().getDato().compareTo(data) == 0) {
                if (current.getSiguiente() == fin) {
                    fin = current;
                }
                current.setSiguiente(current.getSiguiente().getSiguiente());
                cantidad--;
                return;
            }
            current = current.getSiguiente();
        }
    }

    public boolean buscarElemento(T data) {
        NodoCola<T> current = inicio;
        while (current != null) {
            if (current.getDato().compareTo(data) == 0) {
                return true;
            }
            current = current.getSiguiente();
        }
        return false;
    }

    public NodoCola<T> obtenerElemento(T data) {
        NodoCola<T> current = inicio;
        while (current != null) {
            if (current.getDato().compareTo(data) == 0) {
                return current;
            }
            current = current.getSiguiente();
        }
        return null;
    }

    public void vaciar() {
        inicio = fin = null;
        cantidad = 0;
    }

    public void mostrar() {
        NodoCola<T> current = inicio;
        while (current != null) {
            System.out.println(current.getDato());
            current = current.getSiguiente();
        }
    }

    public void mostrarREC(NodoCola<T> nodo) {
        if (nodo == null) return;
        System.out.println(nodo.getDato());
        mostrarREC(nodo.getSiguiente());
    }

    public int cantElementos() {
        return cantidad;
    }
}
