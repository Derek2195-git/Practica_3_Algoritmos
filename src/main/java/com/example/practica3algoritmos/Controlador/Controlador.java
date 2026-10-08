package com.example.practica3algoritmos.Controlador;

import com.example.practica3algoritmos.Modelo.DiceGame;
import com.example.practica3algoritmos.Vista.VentanaJuego;
import com.example.practica3algoritmos.Vista.objetosGUI.Graficas.GraficaThroughput;

public class Controlador {
    private DiceGame juego;
    private VentanaJuego ventana;
    private GraficaThroughput graficaThroughput;
    private boolean panelGraficasVisible;
    private Runnable refrescarGrafica;

    // Para diferenciar si debemos lanzar o mover personas
    private boolean debeLanzar;

    public Controlador(DiceGame juego, VentanaJuego ventana) {
        this.juego = juego;
        this.ventana = ventana;
        graficaThroughput = new GraficaThroughput(20);
        panelGraficasVisible = false;
        refrescarGrafica = null;

        debeLanzar = true;

        ventana.alHacerAccion(this::manejarAccionRonda);
        ventana.alReiniciarPartida(this::manejarReinicio);
        ventana.getSeccionGraficas().alMostrarAreaGrafica(this::alternarPanelGraficas);
        ventana.getSeccionGraficas().alMostrarThroughput(this::mostrarThroughput);
    }

    private void manejarAccionRonda() {
        if (debeLanzar) {
            juego.lanzarDados();
            ventana.getSeccionJugadores().redibujarSeccion();
            ventana.cambiarTextoBotonRonda("Mover personas");
            ventana.actualizarLabelRonda(juego.getRondaActual());
            debeLanzar = false;
        } else {
            juego.moverPersonas();
            ventana.getSeccionJugadores().redibujarSeccion();
            ventana.actualizarLabelRonda(juego.getRondaActual());
            refrescarGraficaActual();

            if (juego.getRondaActual() >= 20) {
                ventana.cambiarVisibilidadBotonRonda(false);
                ventana.cambiarVisibilidadBotonReiniciar(true);
            } else {

                ventana.cambiarTextoBotonRonda("Tirar dados");
                debeLanzar = true;
            }


        }

    }

    private void manejarReinicio() {
        juego.reiniciarJuego();
        ventana.getSeccionJugadores().redibujarSeccion();
        ventana.actualizarLabelRonda(juego.getRondaActual());

        debeLanzar = true;
        ventana.cambiarTextoBotonRonda("Tirar dados");
        ventana.cambiarVisibilidadBotonRonda(true);
        ventana.cambiarVisibilidadBotonReiniciar(false); // corregido
    }

    private void alternarPanelGraficas() {
        panelGraficasVisible = !panelGraficasVisible;
        ventana.getSeccionGraficas().cambiarVisibilidad(panelGraficasVisible);
    }

    private void mostrarThroughput() {
        graficaThroughput.actualizar(juego.getHistorialThroughput());
        ventana.getSeccionGraficas().mostrarGrafica(graficaThroughput.getGrafica());
        refrescarGrafica = this::mostrarThroughput;
    }

    private void refrescarGraficaActual() {
        if (refrescarGrafica != null) {
            refrescarGrafica.run();
        }
    }
}
