import Estructuras.AB;
import Estructuras.NodoAB;
import Practicos.Practico;

public class Main {
    public static void main(String[] args) {

        // Nodos hojas
        NodoAB<Integer> nodoInt20 = new NodoAB<Integer>(20);
        NodoAB<Integer> nodoInt2 = new NodoAB<Integer>(2);
        NodoAB<Integer> nodoInt4 = new NodoAB<Integer>(4);

        NodoAB<Integer> nodoInt7 = new NodoAB<Integer>(7, nodoInt2, null);
        NodoAB<Integer> nodoInt8 = new NodoAB<Integer>(8, nodoInt4, nodoInt7);
        NodoAB<Integer> nodoInt1 = new NodoAB<Integer>(1, nodoInt20, nodoInt8);

        AB ab = new AB(nodoInt1);
        System.out.println(ab.cantNodos());
        System.out.println(ab.cantHojas());

    }


}