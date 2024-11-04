package estructuras;

import dominio.Jugador;

import java.util.Objects;

public class Vertice implements Comparable<Vertice> {
    private String nombre;
    private String descripcion;
    private Integer latencia = -1;

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

    public int getLatencia() {
        return latencia;
    }

    public void setLatencia(int latencia) {
        this.latencia = latencia;
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
        return nombre + ";" + descripcion;
    }
    

    @Override
    public int compareTo(Vertice otro) {
        return this.nombre.compareTo(otro.nombre);
    }
}
