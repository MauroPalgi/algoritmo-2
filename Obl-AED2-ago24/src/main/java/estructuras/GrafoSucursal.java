package estructuras;

public class GrafoSucursal extends Grafo {
    public GrafoSucursal(int cantMaxVertices, boolean esDirigido) {
        super(cantMaxVertices, esDirigido);
    }

    public ABBSucursal obtenerABBSucursalesMenorLatencia(Sucursal vInicial, int latenciaLimite) {
        boolean[] visitados = new boolean[this.maxVertices];
        int[] costos = new int[maxVertices];
        int[] vengo = new int[maxVertices];
        ABBSucursal sucursales = new ABBSucursal();

        for (int i = 0; i < maxVertices; i++) {
            costos[i] = Integer.MAX_VALUE;
            vengo[i] = -1;
            visitados[i] = false;
        }

        int posOrigen = obtenerPos(vInicial);
        costos[posOrigen] = 0;
        vertices[posOrigen].setLatencia(0);
        sucursales.insertar(vertices[posOrigen]);
        for (int i = 0; i < cantVertices; i++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorCosto(costos, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < maxVertices; j++) {
                    if (conexions[pos][j].isExiste() && !visitados[j]) {
                        int distanciaNueva = costos[pos] + conexions[pos][j].getPeso();
                        if (distanciaNueva <= latenciaLimite && costos[j] > distanciaNueva) {
                            vertices[j].setLatencia(distanciaNueva);
                            sucursales.insertar(vertices[j]);
                            costos[j] = distanciaNueva;
                            vengo[j] = pos;
                        }
                    }
                }
            }
        }
        sucursales.listarAscendente();
        return sucursales;
    }
}
