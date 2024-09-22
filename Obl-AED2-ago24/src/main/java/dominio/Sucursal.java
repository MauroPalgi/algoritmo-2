package dominio;

public class Sucursal implements Comparable<Sucursal> {
    private String codigo;
    private String nombre;

    @Override
    public String toString() {
        return codigo + ';' + nombre;
    }

    @Override
    public int compareTo(Sucursal o) {
        return 0;
    }
}
