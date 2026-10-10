package com.example.practica3algoritmos.Modelo;

import java.util.ArrayList;

public class DiceGame {
    private ArrayList<Jugador> jugadores;
    private int rondaActual;
    private int numeroSiguientePersona;
    private ArrayList<Persona> personasSalidas;
    private ArrayList<Integer> historialThroughput;
    private ArrayList<Integer> historialPersonasEnSistema;

    public DiceGame() {
        jugadores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jugadores.add(new Jugador(i + 1));
        }
        rondaActual = 0;
        numeroSiguientePersona = 1;
        personasSalidas = new ArrayList<>();
        historialThroughput = new ArrayList<>();
        historialPersonasEnSistema = new ArrayList<>();
    }

    public void insertarPersonasIniciales() {
        for (Jugador j : jugadores) {
            if (j.getNumero() == 1) continue;
            for (int i = 0; i < 4; i++) {
                j.getColaPersonas().insertarCircular(
                        new Persona(0));
            }
        }
        historialPersonasEnSistema.add(calcularPersonasEnElSistema());
    }

    public void avanzarRonda() {
        rondaActual++;
    }

    public void moverPersonas() {
        avanzarRonda();
        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador j = jugadores.get(i);
            int valorDado = j.getValorDados();
            // El primer jugador tiene una pila prácticamente infitina de gente
            // esperandolo afuera
            if (i == 0) {
                for (int k = 0; k < valorDado; k++) {
                    jugadores.get(1).getColaPersonas().insertarCircular(
                            new Persona(rondaActual));
                }
                j.setPersonasMovidasEnRondaAnterior(valorDado);
                j.añadirActividad(valorDado, valorDado);
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
                j.setPersonasMovidasEnRondaAnterior(cantidadProcesable);
                j.añadirActividad(valorDado, cantidadProcesable);
            }
        }
        historialThroughput.add(calcularThroughput());
        historialPersonasEnSistema.add(calcularPersonasEnElSistema());
    }

    public boolean moverDados(Dado dado, Jugador jugadorOrigen, Jugador jugadorDestino) {
        if (jugadorOrigen == jugadorDestino || !jugadorOrigen.quitarDado(dado)) return false;
        jugadorDestino.agregarDado(dado);
        return true;
    }

    public void lanzarDados() {
        for (Jugador j : jugadores) {
            for (Dado d : j.getDadosActuales()) {
                d.tirarDado();
            }
        }
    }

    public void reiniciarJuego() {
        rondaActual = 0;
        numeroSiguientePersona = 0;
        personasSalidas.clear();
        historialThroughput.clear();
        historialPersonasEnSistema.clear();

        for (Jugador j : jugadores) {
            j.reiniciarJugador();
        }

        insertarPersonasIniciales();
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

    public int calcularPersonasEnElSistema() {
        int total = 0;
        for (int i = 1; i < jugadores.size(); i++) {
            total += jugadores.get(i).getColaPersonas().tamanoCola();
        }
        return total;
    }

    public ArrayList<Integer> getHistorialThroughput() {
        return historialThroughput;
    }

    public ArrayList<Integer> getHistorialPersonasEnSistema() {
        return historialPersonasEnSistema;
    }

    public ArrayList<Integer> getTiemposEnSistema() {
        ArrayList<Integer> tiempos = new ArrayList<>();
        for (Persona p : personasSalidas) {
            if (!p.esPersonaBase()) {
                tiempos.add(p.getRondaSalida() - p.getRondaEntrada());
            }
        }
        return  tiempos;
    }

}
