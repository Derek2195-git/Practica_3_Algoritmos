package com.example.practica3algoritmos.Controlador;

import com.example.practica3algoritmos.Modelo.Dado;
import com.example.practica3algoritmos.Modelo.DiceGame;
import com.example.practica3algoritmos.Modelo.Jugador;
import com.example.practica3algoritmos.Vista.VentanaJuego;
import com.example.practica3algoritmos.Vista.objetosGUI.Graficas.GraficaActividad;
import com.example.practica3algoritmos.Vista.objetosGUI.Graficas.GraficaPersonasEnSistema;
import com.example.practica3algoritmos.Vista.objetosGUI.Graficas.GraficaThroughput;
import com.example.practica3algoritmos.Vista.objetosGUI.Graficas.GraficaTiempoEnSistema;

import java.util.ArrayList;

public class Controlador {
    private DiceGame juego;
    private VentanaJuego ventana;
    private GraficaThroughput graficaThroughput;
    private GraficaPersonasEnSistema graficaPES;
    private GraficaActividad graficaActividad;
    private GraficaTiempoEnSistema graficaTiS;
    private boolean panelGraficasVisible;
    private Runnable refrescarGrafica;
    private final int RONDAS_TOTALES = 20;
    private Dado dadoSeleccionado;
    private Jugador jugadorOrigen;

    // Para diferenciar si debemos lanzar o mover personas
    private boolean debeLanzar;

    public Controlador(DiceGame juego, VentanaJuego ventana) {
        this.juego = juego;
        this.ventana = ventana;
        graficaThroughput = new GraficaThroughput(RONDAS_TOTALES);
        graficaPES = new GraficaPersonasEnSistema(RONDAS_TOTALES);
        graficaActividad = new GraficaActividad(RONDAS_TOTALES, juego.getJugadores().size());
        graficaTiS = new GraficaTiempoEnSistema(34);
        panelGraficasVisible = false;
        refrescarGrafica = null;

        debeLanzar = true;

        ventana.alHacerAccion(this::manejarAccionRonda);
        ventana.alReiniciarPartida(this::manejarReinicio);
        ventana.getSeccionGraficas().alMostrarAreaGrafica(this::alternarPanelGraficas);
        ventana.getSeccionGraficas().alMostrarThroughput(this::mostrarThroughput);
        ventana.getSeccionGraficas().alMostrarDentroSistema(this::mostrarPES);
        ventana.getSeccionGraficas().alMostrarGraficaMovimiento(this::mostrarActividad);
        ventana.getSeccionGraficas().alMostrarGraficaTiempo(this::mostrarTiS);
        ventana.getSeccionJugadores().alSeleccionarDado(this::manejarClickDado);
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

            if (juego.getRondaActual() >= RONDAS_TOTALES) {
                ventana.cambiarVisibilidadBotonRonda(false);
                ventana.cambiarVisibilidadBotonReiniciar(true);
            } else {

                ventana.cambiarTextoBotonRonda("Tirar dados");
                debeLanzar = true;
            }


        }

    }

    private void manejarReinicio() {
        deseleccionar();
        juego.reiniciarJuego();
        ventana.getSeccionJugadores().redibujarSeccion();
        ventana.actualizarLabelRonda(juego.getRondaActual());

        debeLanzar = true;
        ventana.cambiarTextoBotonRonda("Tirar dados");
        ventana.cambiarVisibilidadBotonRonda(true);
        ventana.cambiarVisibilidadBotonReiniciar(false);
        refrescarGraficaActual();
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

    private void mostrarPES() {
        graficaPES.actualizar(juego.getHistorialPersonasEnSistema());
        ventana.getSeccionGraficas().mostrarGrafica(graficaPES.getGrafica());
        refrescarGrafica = this::mostrarPES;
    }

    private void mostrarActividad() {
        ArrayList<ArrayList<Integer>> movidas = new ArrayList<>();
        ArrayList<ArrayList<Integer>> dados = new ArrayList<>();

        for (Jugador j : juego.getJugadores()) {
            movidas.add(j.getHistorialMovidas());
            dados.add(j.getHistorialDados());
        }
        graficaActividad.actualizar(dados, movidas);
        ventana.getSeccionGraficas().mostrarGrafica(graficaActividad.getGrafica());
        refrescarGrafica = this::mostrarActividad;
    }

    private void mostrarTiS() {
        graficaTiS.actualizar(juego.getTiemposEnSistema());
        ventana.getSeccionGraficas().mostrarGrafica(graficaTiS.getGrafica());
        refrescarGrafica = this::mostrarTiS;
    }

    private void refrescarGraficaActual() {
        if (refrescarGrafica != null) {
            refrescarGrafica.run();
        }
    }

    private void manejarClickDado(Jugador jugador, Dado dado) {
        if (dado != null && dado == dadoSeleccionado) {
            deseleccionar();
            return;
        }

        if (dadoSeleccionado == null) {
            if (dado != null) seleccionar(jugador, dado);
            return;
        }

        if (jugador == jugadorOrigen) {
            if (dado != null) seleccionar(jugador, dado);
            return;
        }

        juego.moverDados(dadoSeleccionado, jugadorOrigen, jugador);
        deseleccionar();
        ventana.getSeccionJugadores().redibujarSeccion();

    }

    private void deseleccionar() {
        dadoSeleccionado = null;
        jugadorOrigen = null;
        ventana.getSeccionJugadores().resaltarDado(null);
    }

    private void seleccionar(Jugador jugador, Dado dado) {
        dadoSeleccionado = dado;
        jugadorOrigen = jugador;
        ventana.getSeccionJugadores().resaltarDado(dado);
    }
}
