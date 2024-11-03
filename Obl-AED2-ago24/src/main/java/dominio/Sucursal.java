package dominio;

import estructuras.ListaDoble;
import estructuras.NodoDoble;

public class Sucursal implements Comparable<Sucursal> {
    private String codigo;
    private String nombre;
    private ListaDoble<Conexion> conexiones = new ListaDoble<>();

    public Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Sucursal(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void agregarConexion(Sucursal destino, int latencia) {
        conexiones.agregarFinal(new Conexion(destino, latencia));
    }

    public boolean estaConectadaA(Sucursal destino) {
        NodoDoble<Conexion> actual = conexiones.getInicio();
        while (actual != null) {
            if (actual.getDato().getDestino().equals(destino)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public ListaDoble<Conexion> getConexiones() {
        return conexiones;
    }

    @Override
    public String toString() {
        return codigo + ';' + nombre;
    }

    @Override
    public int compareTo(Sucursal o) {
        return this.codigo.compareTo(o.codigo);
    }
}
