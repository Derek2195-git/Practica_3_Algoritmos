package com.example.practica3algoritmos.Modelo;

import java.util.ArrayList;

public class Jugador {
    private int numero;
    private Dado dadoOriginalJugador;
    private ArrayList<Dado> dadosActuales;
    private ColaCircular<Persona> colaPersonas;
    private int personasMovidasEnRondaAnterior;
    public ArrayList<Integer> historialDados;
    public ArrayList<Integer> historialMovidas;

    public Jugador(int numero) {
        this.numero = numero;
        dadoOriginalJugador = new Dado();
        dadosActuales = new ArrayList<>();
        colaPersonas = new ColaCircular<>();
        historialDados = new ArrayList<>();
        historialMovidas = new ArrayList<>();
        dadosActuales.add(dadoOriginalJugador);
    }

    public int getNumero() {
        return numero;
    }

    public Dado getDadoJugador() {
        return dadoOriginalJugador;
    }

    public ArrayList<Dado> getDadosActuales() { return dadosActuales; }

    public ColaCircular<Persona> getColaPersonas() {
        return colaPersonas;
    }

    public int getPersonasMovidasEnRondaAnterior() {
        return personasMovidasEnRondaAnterior;
    }

    public void setPersonasMovidasEnRondaAnterior(int personasMovidasEnRondaAnterior) {
        this.personasMovidasEnRondaAnterior = personasMovidasEnRondaAnterior;
    }

    public int getValorDados() {
        int total = 0;
        for (Dado dado : dadosActuales) total += dado.getValor();
        return total;
    }

    public void agregarDado(Dado dado) {
        dadosActuales.add(dado);
    }

    public boolean quitarDado(Dado dado) {
        return dadosActuales.remove(dado);
    }

    @Override
    public String toString() {
        if (numero == 1) {
            return "Jugador " + numero + ": "+ dadoOriginalJugador.getValor()
                    + " | (fuente externa, sin cola propia)";
        } else return "Jugador " + numero + ": \n" + dadoOriginalJugador.getValor() +
                " | Tamaño cola: " + colaPersonas.tamanoCola();


    }

    public void reiniciarJugador() {
        historialDados.clear();
        historialMovidas.clear();
        colaPersonas.vaciar();
        dadosActuales.clear();
        dadosActuales.add(dadoOriginalJugador);

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
