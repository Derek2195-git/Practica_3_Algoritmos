package com.example.practica3algoritmos.Vista.secciones;

import com.example.practica3algoritmos.Modelo.Persona;
import com.example.practica3algoritmos.Vista.objetosGUI.PersonaGUI;
import com.example.practica3algoritmos.Vista.objetosGUI.TipoFicha;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SeccionSalida extends Seccion {
    private static final int COLUMNAS = 10;
    private static final int TAMANO_FICHAS = 8;

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
        pilaPersonas.setAlignment(Pos.BOTTOM_CENTER);
        pilaPersonas.getStyleClass().add("panel-jugador");
    }

    @Override
    public void redibujarSeccion() {
        pilaPersonas.getChildren().clear();

        int total = personasSalidas.size();
        int filas = (total + COLUMNAS - 1) / COLUMNAS;

        for (int i = 0; i < total; i++) {
            Persona persona = personasSalidas.get(i);
            TipoFicha tipo = persona.esPersonaBase() ?
                    TipoFicha.BASE : TipoFicha.NORMAL;

            int columna = i % COLUMNAS;
            int filaDesdeAbajo = i / COLUMNAS;
            pilaPersonas.add(new PersonaGUI(TAMANO_FICHAS, tipo), columna, filas - 1 - filaDesdeAbajo);
        }
    }

    public GridPane getContenedor() {
        return pilaPersonas;
    }

}
