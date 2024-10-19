package utils;

public class UTILS {
    public static boolean esStringVacioONull(String valor) {
        return valor == null || valor.isEmpty();
    }

    public static boolean validarStringParametros(String... parametros) {
        for (String parametro : parametros) {
            System.out.println(parametro);
            System.out.println(esStringVacioONull(parametro));
            if (parametro == null || parametro.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
