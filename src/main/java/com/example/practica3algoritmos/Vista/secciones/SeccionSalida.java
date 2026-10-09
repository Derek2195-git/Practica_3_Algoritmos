package com.example.practica3algoritmos.Vista.secciones;

import com.example.practica3algoritmos.Modelo.Persona;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SeccionSalida extends Seccion {
    private static final int COLUMNAS = 10;
    private static final int FILAS = 8;

    private ArrayList<Persona> personasSalidas;
    private GridPane pilaPersonas;

    public SeccionSalida(ArrayList<Persona> personasSalidas) {
        this.personasSalidas = personasSalidas;
        crearSeccion();
    }

    @Override
    public void crearSeccion() {
        pilaPersonas = new GridPane();
        pilaPersonas.setHgap(2);
        pilaPersonas.setVgap(2);
    }

    @Override
    public void redibujarSeccion() {

    }

}
