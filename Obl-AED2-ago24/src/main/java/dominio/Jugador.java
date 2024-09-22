package dominio;

import interfaz.Categoria;

public class Jugador implements Comparable<Jugador> {
    private String alias;
    private String nombre;
    private String apellido;
    private Categoria categoria;

    public Jugador(String alias) {
        this.alias = alias;
    }

    public Jugador(String alias, String nombre, String apellido, Categoria categoria) {
        this.alias = alias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return alias + ";" + nombre + ";" + apellido + ";" + categoria.toString();
    }

    @Override
    public int compareTo(Jugador o) {
        return this.alias.compareTo(o.alias);
    }
}
