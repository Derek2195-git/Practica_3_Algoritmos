package com.example.practica3algoritmos.Vista.objetosGUI;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;

public abstract class Grafica {
    protected BarChart<Integer, Integer> grafica;
    protected NumberAxis ejeX;
    protected NumberAxis ejeY;

    public Grafica(String titulo, String labelX, String labelY) {
        ejeX = new NumberAxis();
        ejeX.setLabel(labelX);

        ejeY = new NumberAxis();
        ejeY.setLabel(labelY);
    }
}
