package utils;

public class UTILS {
    public static boolean esStringVacioONull(String valor) {
        return valor == null || valor.isEmpty();
    }

    public static boolean validarStringParametros(String... parametros) {
        for (String parametro : parametros) {
            if (!esStringVacioONull(parametro)) {
                return false;
            }
        }
        return true;
    }
}
