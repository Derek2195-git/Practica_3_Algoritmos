package com.example.practica3algoritmos.Vista.objetosGUI.Graficas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GraficaActividad extends Grafica {

    private BorderPane contenedor;
    private Label labelPromedio;
    private ArrayList<ArrayList<Integer>> historialMovidas;
    private ArrayList<ArrayList<Integer>> historialDados;
    private int jugadorSeleccionado;
    private boolean mostrarMovidas;
    private final int MOSTRAR_TODOS = -1;

    public GraficaActividad(int rondasTotales, int numJugadores) {
        super("Actividad", "Ronda", "Cantidad", crearCategorias(1, rondasTotales));

        historialMovidas = new ArrayList<>();
        historialDados = new ArrayList<>();
        jugadorSeleccionado = MOSTRAR_TODOS;
        mostrarMovidas = false;

        ejeY.setAutoRanging(false);
        ejeY.setLowerBound(0);
        ejeY.setUpperBound(36);
        ejeY.setTickUnit(2);
        ejeY.setMinorTickCount(2);

        grafica.setVerticalGridLinesVisible(false);
        grafica.setCategoryGap(10);
        grafica.setBarGap(0);

        construirGrafica(numJugadores);
    }

    private void construirGrafica(int numJugadores){
        ToggleGroup tipoGrafica = new ToggleGroup();
        ToggleButton btnMovidas = new ToggleButton("Movidas");
        ToggleButton btnDados = new ToggleButton("Dados");

        btnMovidas.setToggleGroup(tipoGrafica);
        btnDados.setToggleGroup(tipoGrafica);
        btnDados.setSelected(true);
        mantenerSeleccionado(tipoGrafica);

        btnMovidas.setOnAction(e -> {
            mostrarMovidas = true;
            redibujar();
        });

        btnDados.setOnAction(e -> {
            mostrarMovidas = false;
            redibujar();
        });

        // parte de la derecha
        labelPromedio = new Label("0.0");
        VBox panelDerecho = new VBox(8, btnDados, btnMovidas, new Label("Promedio"), labelPromedio);
        panelDerecho.setAlignment(Pos.CENTER);
        panelDerecho.setPadding(new Insets(0,0,0,8));

        // Parte de abajo
        ToggleGroup parteJugadores = new ToggleGroup();
        HBox barraJugadores = new HBox(4, new Label("Jugador"));
        for (int i = 0; i < numJugadores; i++) {
            int indice = i;
            ToggleButton botonJugador = new ToggleButton(String.valueOf(i+1));
            botonJugador.setToggleGroup(parteJugadores);
            botonJugador.setOnAction(e -> {
                jugadorSeleccionado = indice;
                redibujar();
            });
            barraJugadores.getChildren().add(botonJugador);
        }

        ToggleButton botonTodos = new ToggleButton("Todos");
        botonTodos.setToggleGroup(parteJugadores);
        botonTodos.setSelected(true);
        botonTodos.setOnAction(e -> {
            jugadorSeleccionado = MOSTRAR_TODOS;
            redibujar();
        });
        barraJugadores.getChildren().add(botonTodos);
        mantenerSeleccionado(parteJugadores);
        barraJugadores.setAlignment(Pos.CENTER);
        barraJugadores.setPadding(new Insets(8,0,0,0));

        contenedor = new BorderPane();
        contenedor.setCenter(grafica);
        contenedor.setRight(panelDerecho);
        contenedor.setBottom(barraJugadores);
    }

    private void mantenerSeleccionado(ToggleGroup grupoAMantener) {
        grupoAMantener.selectedToggleProperty().addListener((obs, ant, n) -> {
            if (n == null && ant != null) ant.setSelected(true);
        });
    }

    private void redibujar() {
        ArrayList<Double> valores = calcularValores(mostrarMovidas ? historialMovidas : historialDados);

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        double suma = 0;
        for (int i = 0; i < valores.size(); i++) {
            serie.getData().add(new XYChart.Data<>(String.valueOf(i+1), valores.get(i)));
            suma += valores.get(i);
        }

        grafica.getData().clear();
        grafica.getData().add(serie);

        // me voamater
        labelPromedio.setText(String.format("%.1f", valores.isEmpty() ? 0 : suma / valores.size()));
    }

    private ArrayList<Double> calcularValores(ArrayList<ArrayList<Integer>> historialACalcular) {
        ArrayList<Double> valores = new ArrayList<>();
        if(historialACalcular.isEmpty()) return valores;

        if(jugadorSeleccionado != MOSTRAR_TODOS) {
            for (int v : historialACalcular.get(jugadorSeleccionado)) valores.add((double) v);
            return valores;
        }

        int rondas = historialACalcular.get(0).size();
        for (int r = 0; r < rondas; r++) {
            double suma = 0;
            for (ArrayList<Integer> jugador : historialACalcular) suma += jugador.get(r);
            valores.add(suma / historialACalcular.size());
        }

        return valores;
    }

    public void actualizar(ArrayList<ArrayList<Integer>> historialDados,
                           ArrayList<ArrayList<Integer>> historialMovidas) {
        this.historialDados = historialDados;
        this.historialMovidas = historialMovidas;
        redibujar();
    }

    @Override
    public Node getGrafica() {
        return contenedor;
    }




}
