package com.example.practica3algoritmos.Vista.objetosGUI.Graficas;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class GraficaTiempoEnSistema extends Grafica {
    private final int maxPersonas;

    public GraficaTiempoEnSistema(int maxPersonas) {
        super("Tiempo de una persona en el sistema", "Orden de llegada",
                "Tiempo en el sistema", crearCategorias(1, maxPersonas));
        this.maxPersonas = maxPersonas;

        ejeY.setAutoRanging(false);
        ejeY.setLowerBound(9);
        ejeY.setUpperBound(20);
        ejeY.setTickUnit(1);
        ejeY.setMinorTickCount(0);

        grafica.setVerticalGridLinesVisible(false);
        grafica.setCategoryGap(5);
        grafica.setBarGap(0);
    }

    public void actualizar(ArrayList<Integer> tiempos) {
        XYChart.Series<String, Number> serie = new XYChart.Series<>();

        int total = Math.min(tiempos.size(), maxPersonas);
        for (int i = 0; i < total; i++) {
            serie.getData().add(new XYChart.Data<>(String.valueOf(i+1), tiempos.get(i)));
        }

        grafica.getData().clear();
        grafica.getData().add(serie);
    }
}
