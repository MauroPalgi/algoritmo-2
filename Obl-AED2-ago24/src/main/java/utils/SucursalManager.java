package utils;

import estructuras.ABB;
import estructuras.Sucursal;

public class SucursalManager {
    private int maxLatencia; 
    private ABB<Sucursal> arbolSucursales; 

    public SucursalManager(int maxLatencia) {
        this.maxLatencia = maxLatencia;
        this.arbolSucursales = new ABB<>(); 
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
        arbolSucursales.insertar(sucursal); 
    }
}

