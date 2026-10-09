package com.example.practica3algoritmos.Modelo;

public class ColaCircular<T> {
    private T[] objetos;
    private int inicio, fin, cantidad;

    public ColaCircular(int capacidad) {
        objetos = (T[]) new Object[capacidad];
        inicio = -1;
        fin = -1;
        cantidad = 0;
    }

    public ColaCircular() {
        objetos = (T[]) new Object[200];
        inicio = -1;
        fin = -1;
        cantidad = 0;
    }

    public void insertarCircular(T dato) {
        if (((fin == objetos.length - 1) && (inicio == 0)) || (fin == inicio - 1)) {
            return;
        } else {
            if (fin == objetos.length - 1) {
                fin = 0;
            } else {
                fin += 1;
            }
            objetos[fin] = dato;
            cantidad++;
            if (inicio == -1) inicio = 0;
        }
    }

    public T eliminarCircular() {
        if (inicio == -1) return null;
        else {
            T dato = objetos[inicio];
            if (inicio == fin) {
                inicio = -1;
                fin = -1;
            } else {

                if (inicio == objetos.length - 1) {
                    inicio = 0;
                } else {
                    inicio += 1;
                }
            }
            cantidad--;
            return dato;
        }
    }

    public int tamanoCola() {
        return cantidad;
    }

    public void vaciar() {
        objetos = (T[]) new Object[objetos.length];
        inicio = -1;
        fin = -1;
        cantidad = 0;
    }

    public T peekObjeto(int indice) {
        if (indice < 0 || indice >= cantidad) return null;
        int posicion = inicio + indice;
        // Si la posición es mayor al tamaño, simplemente le restamos el tamaño para que sea "circular"
        if (posicion >= objetos.length) posicion -= objetos.length;
        return objetos[posicion];
    }

    public T peek() {
        return objetos[cantidad];
    }

}
