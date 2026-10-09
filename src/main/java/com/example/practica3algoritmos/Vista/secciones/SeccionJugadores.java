package com.example.practica3algoritmos.Vista.secciones;

import com.example.practica3algoritmos.Modelo.Dado;
import com.example.practica3algoritmos.Modelo.Jugador;
import com.example.practica3algoritmos.Vista.objetosGUI.PanelJugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class SeccionJugadores extends Seccion {
    private ArrayList<PanelJugador> panelJugadores;
    private ArrayList<Jugador> jugadores;
    private GridPane cuadricula;

    public SeccionJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores= jugadores;
        panelJugadores = new ArrayList<>();
        crearSeccion();
    }

    public void alSeleccionarDado(BiConsumer<Jugador, Dado> accion) {
        for (PanelJugador jugador : panelJugadores) {
            jugador.alSeleccionarDado(dado -> {
                accion.accept(jugador.getJugador(), dado);
            });
        }
    }

    public void resaltarDado(Dado dado) {
        for (PanelJugador jugador : panelJugadores) {
            jugador.resaltarDado(dado);
        }
    }

    @Override
    protected void crearSeccion() {
        cuadricula = new GridPane();
        cuadricula.setHgap(8);
        cuadricula.setVgap(8);
        cuadricula.setAlignment(Pos.CENTER);
        cuadricula.setPadding(new Insets(8));

        for(Jugador j : jugadores) {
            PanelJugador panel = new PanelJugador(j);
            panelJugadores.add(panel);

            int numero = j.getNumero();
            int fila, columna;

            // Para darle una estetica parecida al juego original, voy a intentar hacerlo en el mismo orden
            if (numero <= 5) {
                fila = 0;
                columna = numero - 1;
            } else if (numero == 6) {
                fila = 1;
                columna = 4;
            } else {
                fila = 2;
                columna = 4 - (numero - 7);
            }

            cuadricula.add(panel.getContenedor(), columna, fila);
            GridPane.setValignment(panel.getContenedor(), VPos.TOP);
        }
    }

    @Override
    public void redibujarSeccion() {
        for (PanelJugador panel : panelJugadores) {
            panel.redibujar();
        }
    }

    public GridPane getContenedor() {
        return cuadricula;
    }

    public ArrayList<PanelJugador> getPanelJugadores() {
        return panelJugadores;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
}
