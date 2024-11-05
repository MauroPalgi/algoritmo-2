package estructuras;

public class ABBSucursal extends ABB<Sucursal>{
    
    public Sucursal obtenerVerticeMayorLatencia() {
        return obtenerVerticeMayorLatencia(this.getRaiz());
    }

    
    private Sucursal obtenerVerticeMayorLatencia(NodoABB<Sucursal> nodo) {
        if (nodo == null) {
            return null; 
        }

        
        Sucursal sucursalMayorLatencia = nodo.getDato();

        
        Sucursal mayorIzquierda = obtenerVerticeMayorLatencia(nodo.getIzq());
        if (mayorIzquierda != null && mayorIzquierda.getLatencia() > sucursalMayorLatencia.getLatencia()) {
            sucursalMayorLatencia = mayorIzquierda;
        }

        
        Sucursal mayorDerecha = obtenerVerticeMayorLatencia(nodo.getDer());
        if (mayorDerecha != null && mayorDerecha.getLatencia() > sucursalMayorLatencia.getLatencia()) {
            sucursalMayorLatencia = mayorDerecha;
        }

        return sucursalMayorLatencia;
    }

}
