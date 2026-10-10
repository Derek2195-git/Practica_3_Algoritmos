package com.example.practica3algoritmos.Modelo;

public class Persona {
    // honestamente, voy a dejar sencilla la persona solo para distinguir
    // Cuando ha entrado y cuando ha salido para las graficas

    private int rondaEntrada;
    private int rondaSalida;

    public Persona(int rondaEntrada) {
        this.rondaEntrada = rondaEntrada;
        rondaSalida = -1;
    }
    public int getRondaEntrada() {
        return rondaEntrada;
    }

    public int getRondaSalida() {
        return rondaSalida;
    }

    public void setRondaEntrada(int rondaEntrada) {
        this.rondaEntrada = rondaEntrada;
    }

    public void setRondaSalida(int rondaSalida) {
        this.rondaSalida = rondaSalida;
    }

    public boolean sigueDentroDelSistema() {
        return rondaSalida == -1;
    }

    public boolean esPersonaBase() { return rondaEntrada == 0; }

    @Override
    public String toString() {
        return "Persona:  [ronda de entrada: " +
                rondaEntrada + ", ronda de salida: " + rondaSalida + "]";
    }
}
