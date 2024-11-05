package estructuras;

import interfaz.ILista;

public class GrafoSimple {

    private boolean[] vertices;
    private int[][] aristas;
    private final boolean esDirigido;
    private int cantidadDeVertices;
    private final int cantMaxVertices;

    public GrafoSimple(int cantMaxDeVertices, boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.cantMaxVertices = cantMaxDeVertices;
        vertices = new boolean[cantMaxDeVertices + 1];
        aristas = new int[cantMaxDeVertices + 1][cantMaxDeVertices + 1];
    }

    public void agregarVertices(int vertice) {
        vertices[vertice] = true;
    }

    public void borrarVertice(int vertice) {
        vertices[vertice] = false;
        
    }

    public void agregarArista(int vInicial, int vFinal) {
        aristas[vInicial][vFinal] = 1;
        if (!esDirigido) {
            aristas[vFinal][vInicial] = 1;
        }
    }

    public void borrarArista(int vInicial, int vFinal) {
        aristas[vInicial][vFinal] = 0;
        if (!esDirigido) {
            aristas[vFinal][vInicial] = 0;
        }
    }

    public ILista<Integer> adyacentes(int vertice) {
        ILista<Integer> adyacentes = new Lista<>();

        for (int i = 1; i < aristas.length; i++) {
            if (aristas[vertice][i] == 1) {
                adyacentes.insertar(i);
            }
        }
        return adyacentes;
    }


}
