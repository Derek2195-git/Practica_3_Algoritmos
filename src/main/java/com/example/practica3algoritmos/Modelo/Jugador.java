package com.example.practica3algoritmos.Modelo;

import java.util.ArrayList;

public class Jugador {
    private int numero;
    private Dado dadoJugador;
    private ColaCircular<Persona> colaPersonas;
    private int personasMovidasEnRondaAnterior;
    public ArrayList<Integer> historialDados;
    public ArrayList<Integer> historialMovidas;

    public Jugador(int numero) {
        this.numero = numero;
        dadoJugador = new Dado();
        colaPersonas = new ColaCircular<>();
        historialDados = new ArrayList<>();
        historialMovidas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public Dado getDadoJugador() {
        return dadoJugador;
    }

    public ColaCircular<Persona> getColaPersonas() {
        return colaPersonas;
    }

    public int getPersonasMovidasEnRondaAnterior() {
        return personasMovidasEnRondaAnterior;
    }

    public void setPersonasMovidasEnRondaAnterior(int personasMovidasEnRondaAnterior) {
        this.personasMovidasEnRondaAnterior = personasMovidasEnRondaAnterior;
    }

    @Override
    public String toString() {
        if (numero == 1) {
            return "Jugador " + numero + ": "+ dadoJugador.getValor()
                    + " | (fuente externa, sin cola propia)";
        } else return "Jugador " + numero + ": \n" + dadoJugador.getValor() +
                " | Tamaño cola: " + colaPersonas.tamanoCola();


    }

    public void reiniciarJugador() {
        historialDados.clear();
        historialMovidas.clear();
        colaPersonas.vaciar();
        personasMovidasEnRondaAnterior = 0;
    }

    public void añadirActividad(int valorDado, int personasMovidas) {
        historialDados.add(valorDado);
        historialMovidas.add(personasMovidas);
    }

    public ArrayList<Integer> getHistorialDados() {
        return historialDados;
    }

    public ArrayList<Integer> getHistorialMovidas() {
        return historialMovidas;
    }
}
