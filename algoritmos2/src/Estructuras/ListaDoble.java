package Estructuras;

public class ListaDoble<T extends Comparable<T>> {

    private NodoDoble<T> inicio;
    private NodoDoble<T> fin;
    private int cantidad;

    public NodoDoble getInicio() {
        return inicio;
    }

    public void setInicio(NodoDoble<T> inicio) {
        this.inicio = inicio;
    }

    public NodoDoble getFin() {
        return fin;
    }

    public void setFin(NodoDoble<T> fin) {
        this.fin = fin;
    }

    
    public boolean esVacia() { // GOOD
        return this.cantidad == 0;
    }

    
    public void agregarInicio(T data) { // GOOD
        NodoDoble<T> nodo = new NodoDoble<>(data);
        if (esVacia()) {
            this.inicio = nodo;
            this.fin = inicio;
        } else {
            nodo.setSiguiente(this.getInicio());
            nodo.getSiguiente().setAnterior(nodo);
            this.inicio = nodo;
        }
        cantidad++;
    }

    
    public void agregarFinal(T data) { // GOOD
        NodoDoble<T> nodo = new NodoDoble<>(data);
        if (this.esVacia()) {
            setInicio(nodo);
            setFin(nodo);
        } else {
            NodoDoble<T> aux = this.getFin();
            nodo.setAnterior(aux);
            aux.setSiguiente(nodo);
            this.setFin(nodo);
        }
        cantidad++;
    }

    
    public void agregarOrd(T data) { // TODO UTILIZAR https://www.geeksforgeeks.org/insertion-sort-doubly-linked-list/
        NodoDoble<T> nodo = new NodoDoble<>(data);
        if (!esVacia()) {
            NodoDoble actual = getInicio();

            if (actual.getDato().compareTo(data) > 0) {
                agregarInicio(data);
                return;
            }
            if (getFin().getDato().compareTo(data) < 0) {
                agregarFinal(data);
                return;
            }
            while (actual != null) {
                if (actual.getDato().compareTo(nodo.getDato()) == 0) {
                    System.out.println("Ese elemento ya se encuntra en la lista");
                    return;
                }

                if (actual.getDato().compareTo(nodo.getDato()) < 0 && actual.getSiguiente() != null && actual.getSiguiente().getDato().compareTo(nodo.getDato()) > 0) {
                    NodoDoble aux = actual.getSiguiente();
                    actual.setSiguiente(nodo);
                    nodo.setSiguiente(aux);
                    nodo.setAnterior(actual);
                    aux.setAnterior(nodo);
                    cantidad++;
                    return;

                }

                actual = actual.getSiguiente();

            }

        } else {
            agregarInicio(data);
        }

    }

    
    public void borrarInicio() { // GOOD
        if (!this.esVacia()) {
            if (cantidad == 1) {
                setFin(null);
                setInicio(null);
            } else {
                NodoDoble aux = getInicio().getSiguiente();
                setInicio(null);
                setInicio(aux);
                this.inicio.setAnterior(null);
            }

            cantidad--;
        } else {
            System.out.println("Esta vacia");
        }
    }

    
    public void borrarFin() { // GOOD
        if (!this.esVacia()) {
            if (cantidad == 1) {
                setFin(null);
                setInicio(null);
            } else {
                NodoDoble<T> aux = this.getFin().getAnterior();
                this.setFin(null);
                aux.setSiguiente(null);
                this.setFin(aux);
            }
            cantidad--;
        }
    }

    
    public void borrarElemento(T data) { // GOOD
        if (!this.esVacia()) {
            NodoDoble<T> actual = getInicio();
            while (actual != null) {
                if (actual.getDato().equals(data)) {
                    NodoDoble<T> nodoSiguiente = actual.getSiguiente();
                    NodoDoble<T> nodoAnterior = actual.getAnterior();
                    if (nodoSiguiente != null && nodoAnterior != null) {
                        nodoAnterior.setSiguiente(nodoSiguiente);
                        nodoSiguiente.setAnterior(nodoAnterior);
                        cantidad--;
                    }
                    if (cantElementos() == 1) {
                        vaciar();
                    }
                }
                if (getInicio() != null && getInicio().getDato().equals(data)) {
                    borrarInicio();
                }
                if (getFin() != null && getFin().getDato().equals(data)) {
                    borrarFin();
                }
                actual = actual.getSiguiente();

            }

        } else {
            System.out.println("La Lista esta Vacia");
        }

    }

    
    public String toString() {
        String text = "";
        NodoDoble<T> actual = getInicio();
        if (actual != null) {
            text += "Lista Nodos{ \n inicio: " + getInicio().getDato().toString() + ";\n fin: " + getFin().getDato().toString() + ";\n cantidad elementos: " + this.cantidad + " \n= [";
            while (actual != null) {
                text += actual.toString() + "  ";
                actual = actual.getSiguiente();
            }
            text += "\n ]}";
        } else {
            text = "La lista esta vacia";
        }
        return text;
    }

    
    public boolean buscarElemento(T data) { // GOOD
        boolean econtrado = false;
        if (!this.esVacia()) {
            NodoDoble<T> actual = getInicio();
            while (actual != null) {
                if (actual.getDato().equals(data)) {
                    econtrado = true;
                }
                actual = actual.getSiguiente();
            }

        }
        return econtrado;
    }

    
    public NodoDoble obtenerElemento(T data) { // GOOD
        NodoDoble<T> nodo = null;
        if (!this.esVacia()) {
            NodoDoble<T> actual = getInicio();
            while (actual != null) {
                if (actual.getDato().equals(data)) {
                    nodo = actual;
                }
                actual = actual.getSiguiente();
            }

        }
        return nodo;
    }

    
    public void vaciar() { // GOOD
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }

    
    public void mostrar() { // GOOD
        NodoDoble<T> actual = getInicio();
        if (actual != null) {
            System.out.println("Lista Nodos{ \n inicio: " + getInicio().getDato().toString() + ";\n fin: " + getFin().getDato().toString() + ";\n cantidad elementos: " + this.cantidad + " \n= [");
            while (actual != null) {
                System.out.print(actual.toString() + "  ");
                actual = actual.getSiguiente();
            }
            System.out.println("\n ]}");
        } else {
            System.out.println("La lista esta vacia");
        }
    }

    // el nodo recibido tiene que ser el inicio
    
    public void mostrarREC(NodoDoble nodo) { // GOOD
        if (esVacia()) { // es lo mismo que preguntar nodo == null
            System.out.print("La Lista esta vacia");
            return;
        }
        if (nodo.getSiguiente() == null) {
            System.out.println(nodo.toString());
            System.out.print("inicio: " + inicio);
            System.out.print("fin: " + fin);
            System.out.print("cant elementos:: " + cantidad);
            return;
        }
        System.out.print(nodo.toString());
        mostrarREC(nodo.getSiguiente());
    }

    
    public int cantElementos() { // GOOD
        return this.cantidad;
    }

}
