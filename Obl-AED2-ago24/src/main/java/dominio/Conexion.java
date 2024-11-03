package dominio;

public class Conexion implements Comparable<Conexion> {
    private Sucursal destino;
    private int latencia;

    public Conexion(Sucursal destino, int latencia) {
        this.destino = destino;
        this.latencia = latencia;
    }

    public Sucursal getDestino() {
        return destino;
    }

    public int getLatencia() {
        return latencia;
    }

    @Override
    public int compareTo(Conexion otraConexion) {
        return Integer.compare(this.latencia, otraConexion.latencia);
    }
}
