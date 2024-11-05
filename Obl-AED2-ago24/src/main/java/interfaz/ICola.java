package interfaz;

public interface ICola<T> {
    void encolar(T dato);
    T desencolar(); 
    boolean estaVacia();
}
