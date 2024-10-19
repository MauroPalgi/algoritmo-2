package utils;

public class Resultado<T> {
    private T datoEncontrado;
    private int iteraciones;

    public Resultado(T datoEncontrado, int iteraciones) {
        this.datoEncontrado = datoEncontrado;
        this.iteraciones = iteraciones;
    }

    public T getDatoEncontrado() {
        return datoEncontrado;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    @Override
    public String toString() {
        return "Dato: " + datoEncontrado + ", Iteraciones: " + iteraciones;
    }
}
