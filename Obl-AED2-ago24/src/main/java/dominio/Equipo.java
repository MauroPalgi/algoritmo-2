package dominio;

import estructuras.ABBJugador;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String manager;

    public ABBJugador getAbbIntegrantes() {
        return abbIntegrantes;
    }

    private ABBJugador abbIntegrantes = new ABBJugador();

    public Equipo(String nombre, String manager) {
        this.nombre = nombre;
        this.manager = manager;
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getManager() {
        return manager;
    }

    @Override
    public int compareTo(Equipo otro) {
        return this.nombre.compareTo(otro.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equals(equipo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    @Override
    public String toString() {
        return nombre + ";" + manager;
    }


    public Jugador buscarJugador(String alias){
        return abbIntegrantes.buscar(new Jugador(alias));
    }
}
