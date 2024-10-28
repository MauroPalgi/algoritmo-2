package estructuras;

import java.util.Objects;

public class Vertice {
    private String nombre;
    private String descripcion;

    public Vertice(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Vertice(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return Objects.equals(nombre, vertice.nombre);
    }

    @Override
    public String toString() {
        return "Vertice[" + nombre + ", " + descripcion + ']';
    }
}
