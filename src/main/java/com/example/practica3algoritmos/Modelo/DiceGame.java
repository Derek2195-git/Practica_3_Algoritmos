package com.example.practica3algoritmos.Modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DiceGame {
    private ArrayList<Jugador> jugadores;
    private int rondaActual;
    private int numeroSiguientePersona;

    // Para las pruebas por mientras
    public DiceGame(int numJugadores) {
        jugadores = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            jugadores.add(new Jugador(i + 1));
        }
        rondaActual = 0;
        numeroSiguientePersona = 1;
        insertarPersonasIniciales();
    }

    public DiceGame() {
        jugadores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jugadores.add(new Jugador(i + 1));
        }
        rondaActual = 0;
    }

    public void insertarPersonasIniciales() {
        for (Jugador j : jugadores) {
            for (int i = 0; i < 4; i++) {
                Persona persona = new Persona(++numeroSiguientePersona, 0);
                j.getColaPersonas().insertarCircular(persona);
            }
        }
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public int getRondaActual() {
            return rondaActual;
    }
}
