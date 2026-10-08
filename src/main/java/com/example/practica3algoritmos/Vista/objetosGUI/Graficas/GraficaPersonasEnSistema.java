package com.example.practica3algoritmos.Vista.objetosGUI.Graficas;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class GraficaPersonasEnSistema extends Grafica {
    public GraficaPersonasEnSistema(int rondasTotales) {
        super("Personas en el sistema", "Ronda",
                "Personas en el sistema", crearCategorias(0, rondasTotales));

        ejeY.setAutoRanging(false);
        ejeY.setLowerBound(0);
        ejeY.setUpperBound(100);
        ejeY.setTickUnit(10);
        ejeY.setMinorTickCount(5);

        grafica.setVerticalGridLinesVisible(false);
        grafica.setCategoryGap(10);
        grafica.setBarGap(0);
    }

    public void actualizar(ArrayList<Integer> historial) {
        XYChart.Series<String, Number> serie = new XYChart.Series<>();

        for (int i = 0; i < historial.size(); i++) {
            XYChart.Data<String, Number> dato =
                    new XYChart.Data<>(String.valueOf(i), historial.get(i));

            // La barra 0 es el estado inicial, se distingue en gris
            if (i == 0) {
                dato.nodeProperty().addListener((obs, anterior, nodo) -> {
                    if (nodo != null) nodo.setStyle("-fx-bar-fill: #a2a2a2;");
                });
            }
            serie.getData().add(dato);
        }

        grafica.getData().clear();
        grafica.getData().add(serie);
    }
}
