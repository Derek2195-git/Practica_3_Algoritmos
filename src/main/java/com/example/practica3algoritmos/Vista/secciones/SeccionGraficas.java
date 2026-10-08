package com.example.practica3algoritmos.Vista.secciones;

import com.example.practica3algoritmos.Vista.objetosGUI.ImageButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SeccionGraficas extends Seccion {
    private VBox contenedorPrincipal;
    private VBox contenedorBotones;
    private VBox areaGrafica;
    private Button botonMostrarGraficas;
    private Button botonGraficaThroughput;
    private Button botonGraficaDentroSistema;
    private Button botonGraficaTiempo;
    private Button botonGraficaMovimientos;


    public SeccionGraficas() {
        crearSeccion();
    }

    @Override
    protected void crearSeccion() {
        botonMostrarGraficas = new ImageButton("/recursos/iconos/botones/botonMostrarGraficas.png", 38, 102);
        botonGraficaThroughput = new ImageButton("/recursos/iconos/botones/botonThroughput.png", 38, 102);
        botonGraficaDentroSistema = new ImageButton("/recursos/iconos/botones/botonNiS.png", 38, 102);
        botonGraficaTiempo = new ImageButton();
        botonGraficaMovimientos = new ImageButton();

        contenedorBotones = new VBox(5, botonGraficaThroughput, botonGraficaDentroSistema,
                botonGraficaTiempo, botonGraficaMovimientos);
        contenedorBotones.setAlignment(Pos.CENTER);

        areaGrafica = new VBox();
        areaGrafica.setAlignment(Pos.CENTER);

        contenedorPrincipal = new VBox(8, areaGrafica, contenedorBotones);

        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setVisible(false);
        contenedorPrincipal.setManaged(false);

    }

    public void mostrarGrafica(Node grafica) {
        areaGrafica.getChildren().setAll(grafica);
    }

    public void alMostrarAreaGrafica(Runnable accion){ botonMostrarGraficas.setOnAction(e -> accion.run());}

    public void alMostrarThroughput(Runnable accion) {
        botonGraficaThroughput.setOnAction(e -> accion.run());
    }

    public void alMostrarDentroSistema(Runnable accion) { botonGraficaDentroSistema.setOnAction(e -> accion.run());}

    public void alMostrarGraficaTiempo(Runnable accion) { botonGraficaTiempo.setOnAction(e -> accion.run());}

    public void alMostrarGraficaMovimiento(Runnable accion) { botonGraficaMovimientos.setOnAction(e -> accion.run());}

    public void cambiarVisibilidad(boolean esVisible) {
        contenedorPrincipal.setVisible(esVisible);
        contenedorPrincipal.setManaged(esVisible);
    }

    public VBox getContenedorPrincipal() {
        return contenedorPrincipal;
    }

    public VBox getAreaGrafica() {
        return areaGrafica;
    }

    public Button getBotonMostrarGraficas() {
        return botonMostrarGraficas;
    }


}
