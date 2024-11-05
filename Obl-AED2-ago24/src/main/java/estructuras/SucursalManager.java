package estructuras;

public class SucursalManager {
    private int maxLatencia; // Latencia máxima de las ciudades
    private ABB<Sucursal> arbolSucursales; // Árbol que contiene las sucursales

    public SucursalManager(int maxLatencia) {
        this.maxLatencia = maxLatencia;
        this.arbolSucursales = new ABB<>(); // Inicializa el árbol de sucursales
    }

    public int getMaxLatencia() {
        return maxLatencia;
    }

    public void setMaxLatencia(int maxLatencia) {
        this.maxLatencia = maxLatencia;
    }

    public ABB<Sucursal> getArbolSucursales() {
        return arbolSucursales;
    }

    public void agregarSucursal(Sucursal sucursal) {
        arbolSucursales.insertar(sucursal); // Método para agregar una sucursal al árbol
    }
}

