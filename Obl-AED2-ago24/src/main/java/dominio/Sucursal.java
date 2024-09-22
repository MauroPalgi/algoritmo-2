package dominio;

public class Sucursal {
    private String codigo;
    private String nombre;

    @Override
    public String toString() {
        return codigo + ';' + nombre;
    }
}
