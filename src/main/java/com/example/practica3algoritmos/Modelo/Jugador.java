package com.example.practica3algoritmos.Modelo;

public class Jugador {
    private int numero;
    private Dado dadoJugador;
    private ColaCircular<Persona> colaPersonas;
    private int personasMovidasEnRondaAnterior;

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

}
