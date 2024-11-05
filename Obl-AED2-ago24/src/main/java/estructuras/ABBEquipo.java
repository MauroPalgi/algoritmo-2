package estructuras;

import dominio.Equipo;
import dominio.Jugador;

public class ABBEquipo extends ABB<Equipo>{

    public Jugador buscarJugadorEnEquipos(String aliasJugador) {
        return buscarJugadorEnEquipos(this.getRaiz(), aliasJugador);
    }

    private Jugador buscarJugadorEnEquipos(NodoABB<Equipo> nodo, String aliasJugador) {
        if (nodo == null) {
            return null;
        }


        Jugador jugadorABuscar = nodo.getDato().buscarJugador(aliasJugador);
        if (jugadorABuscar != null) {
            return jugadorABuscar;
        }


        Jugador jugadorDerecho = buscarJugadorEnEquipos(nodo.getDer(), aliasJugador);
        if (jugadorDerecho != null) {
            return jugadorDerecho;
        }


        return buscarJugadorEnEquipos(nodo.getIzq(), aliasJugador);
    }
}
