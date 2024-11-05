package utils;

public class UTILS {
    public static boolean esStringVacioONull(String valor) {
        return valor == null || valor.isEmpty();
    }

    public static boolean validarStringParametros(String... parametros) {
        for (String parametro : parametros) {
            if (parametro == null || parametro.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static int findIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i; 
            }
        }
        return -1; 
    }
}
