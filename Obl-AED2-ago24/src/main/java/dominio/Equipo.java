package dominio;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String manager;

    public Equipo(String nombre, String manager) {
        this.nombre = nombre;
        this.manager = manager;
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
}
