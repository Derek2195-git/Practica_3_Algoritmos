package com.example.practica3algoritmos.Modelo;

public class Jugador {
    private String nombre;
    private Dado dadoJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
        dadoJugador = new Dado();
    }

    public String getNombre() {
        return nombre;
    }

    public Dado getDadoJugador() {
        return dadoJugador;
    }


}
