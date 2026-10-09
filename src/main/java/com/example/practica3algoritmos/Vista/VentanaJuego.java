package com.example.practica3algoritmos.Vista;

import com.example.practica3algoritmos.Modelo.DiceGame;
import com.example.practica3algoritmos.Modelo.Jugador;
import com.example.practica3algoritmos.Vista.objetosGUI.PanelJugador;
import com.example.practica3algoritmos.Vista.secciones.SeccionGraficas;
import com.example.practica3algoritmos.Vista.secciones.SeccionJugadores;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class VentanaJuego {
    private DiceGame juego;
    private SeccionJugadores seccionJugadores;
    private SeccionGraficas seccionGraficas;
    private Button botonAccionRonda;
    private Button botonReiniciar;
    private Label labelRonda;

    public VentanaJuego(DiceGame juego) {
        this.juego = juego;

        seccionJugadores = new SeccionJugadores(juego.getJugadores());
        seccionGraficas = new SeccionGraficas();
        botonAccionRonda = new Button("Tirar dados");

        botonReiniciar = new Button("Reiniciar juego");
        botonReiniciar.setVisible(false);
        botonReiniciar.setManaged(false);

        labelRonda = new Label("Ronda 0/20");

        BorderPane ventana = new BorderPane();
        ventana.setTop(labelRonda);
        ventana.setCenter(seccionJugadores.getContenedor());
        ventana.setRight(crearAreaDerecha());

        Scene escena = new Scene(ventana, 800, 600);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        Stage stage = new Stage();
        stage.setScene(escena);
        stage.setTitle("El dado juego");
        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.show();
    }

    private HBox crearAreaDerecha() {
        VBox areaBotones = new VBox(10, botonReiniciar, botonAccionRonda, seccionGraficas.getBotonMostrarGraficas());
        areaBotones.setAlignment(Pos.CENTER);

        HBox areaDerecha = new HBox(8, areaBotones, seccionGraficas.getContenedorPrincipal());
        areaDerecha.setAlignment(Pos.CENTER);
        areaDerecha.setPadding(new Insets(10));

        return areaDerecha;
    }

    public SeccionJugadores getSeccionJugadores() {
        return seccionJugadores;
    }

    public SeccionGraficas getSeccionGraficas() {
        return seccionGraficas;
    }

    public void alHacerAccion(Runnable accion) {
        botonAccionRonda.setOnAction(e -> accion.run());
    }

    public void cambiarTextoBotonRonda(String textoNuevo) {
        botonAccionRonda.setText(textoNuevo);
    }

    public void alReiniciarPartida(Runnable accion) {
        botonReiniciar.setOnAction(e -> accion.run());
    }

    public void cambiarVisibilidadBotonRonda(boolean esVisible) {
        botonAccionRonda.setVisible(esVisible);
        botonAccionRonda.setManaged(esVisible);
    }

    public void cambiarVisibilidadBotonReiniciar(boolean esVisible) {
        botonReiniciar.setVisible(esVisible);
        botonReiniciar.setManaged(esVisible);
    }

    public void actualizarLabelRonda(int rondaActual) {
        labelRonda.setText("Ronda: " + rondaActual + " / 20");
    }
}
