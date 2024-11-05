package estructuras;

import dominio.Equipo;
import dominio.Jugador;

public class ABBEquipo extends ABB<Equipo>{

    public Jugador buscarJugadorEnEquipos(String aliasJugador) {
        return buscarJugadorEnEquipos(this.getRaiz(), aliasJugador);
    }

    private Jugador buscarJugadorEnEquipos(NodoABB<Equipo> nodo, String aliasJugador) {
        if (nodo == null) {
            return null; // Si el nodo es nulo, se retorna null.
        }

        // Buscar el jugador en el equipo actual
        Jugador jugadorABuscar = nodo.getDato().buscarJugador(aliasJugador);
        if (jugadorABuscar != null) {
            return jugadorABuscar; // Si se encontró el jugador, retornar.
        }

        // Buscar en el subárbol derecho
        Jugador jugadorDerecho = buscarJugadorEnEquipos(nodo.getDer(), aliasJugador);
        if (jugadorDerecho != null) {
            return jugadorDerecho; // Si se encontró en el derecho, retornar.
        }

        // Buscar en el subárbol izquierdo
        return buscarJugadorEnEquipos(nodo.getIzq(), aliasJugador); // Retornar lo encontrado en el izquierdo.
    }
}
