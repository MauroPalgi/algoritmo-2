package Practicos;

public class Practico {

    /*
    Ejercicio 2: Matrices
    Implemente un algoritmo que dado una matriz devuelva la suma de todos los elementos
    de sus diagonales. En caso de que la matriz tenga dimensión impar y por lo tanto ambas
    diagonales compartan un mismo elemento, el mismo deberá ser contabilizado solo 1 vez.
    Ejemplos:

    Ejercicio 3: Listas
    A.Realizar una implementación del TAD lista.

    B.Implemente recursivamente una función que inserte (en una lista dinámica) un
    elemento al final. public void insertarFinal(int n);

    C.Implemente de forma recursiva una función que devuelva true si y solo si la lista
    está ordenada de menor a mayor.
    public boolean estaOrdenada();


    Ejercicio 4: Pilas
    A. Realizar dos implementaciones del TAD pila, una estática y otra dinámica.
    B.Implemente una función que permita evaluar si una determinada cadena de
    caracteres está sintácticamente correcta considerando el balanceo de paréntesis. (),
    {}, [].


    Ejercicio 5: Colas
    Realizar una implementación del TAD cola.
    */
    public int sumaVectorIterativo(int[] vector){
        int suma = 0;
        for (int i = 0; i < vector.length; i++) {
            suma += vector[i];
        }

        return suma;
    }

    public int sumaVectorRecursiva(int[] vector){
        int suma = 0;
        for (int i = 0; i < vector.length; i++) {
            suma += vector[i];
        }

        return suma;
    }
}
