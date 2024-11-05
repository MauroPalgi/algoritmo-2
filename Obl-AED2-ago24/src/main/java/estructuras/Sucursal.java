package estructuras;

import java.util.Objects;

public class Sucursal implements Comparable<Sucursal> {
    private String nombre;
    private String descripcion;
    private Integer latencia = -1;

    public Sucursal(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Sucursal(String nombre) {
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
        Sucursal sucursal = (Sucursal) o;
        return Objects.equals(nombre, sucursal.nombre);
    }

    @Override
    public String toString() {
        return nombre + ";" + descripcion;
    }
    

    @Override
    public int compareTo(Sucursal otro) {
        return this.nombre.compareTo(otro.nombre);
    }
}
