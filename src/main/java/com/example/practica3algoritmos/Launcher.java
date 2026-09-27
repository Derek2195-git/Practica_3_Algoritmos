package com.example.practica3algoritmos;

import com.example.practica3algoritmos.Modelo.DiceGame;
import com.example.practica3algoritmos.Vista.VentanaJuego;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stagePrimario) {
        DiceGame juego = new DiceGame(10);
        juego.insertarPersonasIniciales();

        new VentanaJuego(juego);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
