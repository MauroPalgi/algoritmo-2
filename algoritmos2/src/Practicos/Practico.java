package Practicos;

public class Practico {
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
