package com.example.practica3algoritmos.Modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DiceGame {
    private ArrayList<Jugador> jugadores;
    private int rondaActual;
    private int numeroSiguientePersona;
    private ArrayList<Persona> personasSalidas;

    // Para las pruebas por mientras
    public DiceGame(int numJugadores) {
        jugadores = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            jugadores.add(new Jugador(i + 1));
        }
        rondaActual = 0;
        numeroSiguientePersona = 1;
        personasSalidas = new ArrayList<>();
        insertarPersonasIniciales();
    }

    public DiceGame() {
        jugadores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jugadores.add(new Jugador(i + 1));
        }
        rondaActual = 0;
        numeroSiguientePersona = 1;
        personasSalidas = new ArrayList<>();
        insertarPersonasIniciales();
    }

    public void insertarPersonasIniciales() {
        for (Jugador j : jugadores) {
            for (int i = 0; i < 4; i++) {
                j.getColaPersonas().insertarCircular(
                        new Persona(++numeroSiguientePersona, 0));
            }
        }
    }

    public void empezarRonda() {
        rondaActual++;

        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador j = jugadores.get(i);
            int valorDado = j.getDadoJugador().lanzar();
            // El primer jugador tiene una pila prácticamente infitina de gente
            // esperandolo afuera
            if (i == 0) {
                for (int k = 0; k < valorDado; k++) {
                    jugadores.get(1).getColaPersonas().insertarCircular(
                            new Persona(++numeroSiguientePersona, rondaActual));
                }
            } else {
                // Los demas jugadores si se revisan la cantidad de personas que posean antes de procesarlos
                int cantidadProcesable = Math.min(valorDado, j.getColaPersonas().tamanoCola());
                for (int k = 0; k < cantidadProcesable; k++) {
                    Persona persona = j.getColaPersonas().eliminarCircular();
                    if (i == jugadores.size() - 1) {
                        // Si es el ultimo jugador, añadimos a los procesados a otro arraylist para usarlos luego
                        persona.setRondaSalida(rondaActual);
                        personasSalidas.add(persona);
                    } else {
                        // Si no, los insertamos al siguiente jugador
                        jugadores.get(i + 1).getColaPersonas()
                                .insertarCircular(persona);
                    }
                }
            }
        }
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public int getRondaActual() {
            return rondaActual;
    }

    public ArrayList<Persona> getPersonasSalidas() {
        return personasSalidas;
    }

    public int calcularThroughput() {
        return personasSalidas.size();
    }
}
