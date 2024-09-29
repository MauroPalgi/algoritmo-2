package estructuras;

import dominio.Equipo;
import dominio.Jugador;

public class ABBEquipo extends ABB<Equipo>{

    public Jugador buscarJugadorEnEquipos(String aliasJugador){
        return buscarJugadorEnEquipos(this.getRaiz(), aliasJugador);
    }
    private Jugador buscarJugadorEnEquipos(NodoABB<Equipo> nodo ,String aliasJugador){
        if (nodo == null){
            return null;
        }
        Jugador jugadorABuscar = nodo.getDato().buscarJugador(aliasJugador);
        if (jugadorABuscar != null){
            return jugadorABuscar;
        }
        if (nodo.getDer() != null){
            buscarJugadorEnEquipos(nodo.getDer(), aliasJugador);
        }

        if (nodo.getIzq() != null){
            buscarJugadorEnEquipos(nodo.getIzq(), aliasJugador);
        }
        return null;
    }
}
