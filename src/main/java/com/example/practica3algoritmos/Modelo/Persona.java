package com.example.practica3algoritmos.Modelo;

public class Persona {
    // honestamente, voy a dejar sencilla la persona solo para distinguir
    // Cuando ha entrado y cuando ha salido para las graficas
    private int id;
    private int rondaEntrada;
    private int rondaSalida;

    public Persona(int id, int rondaEntrada) {
        this.id = id;
        this.rondaEntrada = rondaEntrada;
        rondaSalida = -1;
    }

    public int getId() {
        return id;
    }

    public int getRondaEntrada() {
        return rondaEntrada;
    }

    public int getRondaSalida() {
        return rondaSalida;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Persona [id = " + id + ", ronda de entrada: " +
                rondaEntrada + ", ronda de salida: " + rondaSalida + "]";
    }
}
