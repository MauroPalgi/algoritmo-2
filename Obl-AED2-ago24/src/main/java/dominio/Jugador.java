package dominio;

public class Jugador {
    private String alias;
    private String nombre;
    private String apellido;
    private Categoria categoria;

    @Override
    public String toString() {
        return alias + ";" + nombre + ";" + apellido + ";" + categoria.toString();
    }
}
