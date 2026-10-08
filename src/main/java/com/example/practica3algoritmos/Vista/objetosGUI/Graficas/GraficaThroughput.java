package com.example.practica3algoritmos.Vista.objetosGUI.Graficas;

import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GraficaThroughput extends Grafica {

    public GraficaThroughput(int rondasTotales) {
        super("Throughput del juego", "Ronda", "Throughput", crearCategorias(rondasTotales));

        ejeY.setAutoRanging(false);
        ejeY.setLowerBound(0);
        ejeY.setUpperBound(70);
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
                    new XYChart.Data<>(String.valueOf(i + 1), historial.get(i));

            // Luego dejo de escribir la cantidad de personas

            serie.getData().add(dato);
        }

        grafica.getData().clear();
        grafica.getData().add(serie);
    }

    // Lo tuve que dejar como estatico para que jalara
    public static ArrayList<String> crearCategorias(int rondasTotales) {
        ArrayList<String> categorias = new ArrayList<>();
        for (int i = 0; i <= rondasTotales; i++) {
            categorias.add(String.valueOf(i));
        }
        return categorias;
    }
}
