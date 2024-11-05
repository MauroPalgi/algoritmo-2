package estructuras;

public class Conexion {
    private int peso;
    private boolean existe;

    public Conexion() {
        existe = false;
    }

    public Conexion(int peso) {
        this.peso = peso;
        this.existe=true;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public String toString() {
        return "Arista[" + peso + ", " + existe + ']';
    }
}
