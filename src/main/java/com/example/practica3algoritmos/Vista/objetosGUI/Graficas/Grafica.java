package com.example.practica3algoritmos.Vista.objetosGUI.Graficas;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.util.ArrayList;

public abstract class Grafica {
    // Al parecer los ejes son de la clase Number??
    protected BarChart<String, Number> grafica;
    protected CategoryAxis ejeX;
    protected NumberAxis ejeY;

    protected Grafica(String titulo, String labelX, String labelY, ArrayList<String> categorias) {
        ejeX = new CategoryAxis();
        ejeX.setLabel(labelX);
        ejeX.setCategories(FXCollections.observableList(categorias));

        ejeY = new NumberAxis();
        ejeY.setLabel(labelY);

        grafica = new BarChart<>(ejeX, ejeY);
        grafica.setTitle(titulo);
        grafica.setAnimated(false);
        grafica.setLegendVisible(false);
        grafica.setPrefSize(500, 350);
    }

    // Lo tuve que dejar como estatico para que jalara
    protected static ArrayList<String> crearCategorias(int inicio, int fin) {
        ArrayList<String> categorias = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            categorias.add(String.valueOf(i));
        }
        return categorias;
    }

    // por si las dudas lo voy a regresar como Node, aunque sea un BarChart
    public Node getGrafica() {
        return grafica;
    }


}
