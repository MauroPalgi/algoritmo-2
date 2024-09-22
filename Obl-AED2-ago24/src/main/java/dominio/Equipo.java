package dominio;

public class Equipo {
    private String nombre;
    private String manager;

    @Override
    public String toString() {
        return nombre + ';' + manager;
    }
}
