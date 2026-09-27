package com.example.practica3algoritmos.Modelo;

public class Jugador {
    private int numero;
    private Dado dadoJugador;
    private ColaCircular<Persona> colaPersonas;

    public Jugador(int numero) {
        this.numero = numero;
        dadoJugador = new Dado();
        colaPersonas = new ColaCircular<>();
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


}
