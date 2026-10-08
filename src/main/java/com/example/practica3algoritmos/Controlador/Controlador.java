package com.example.practica3algoritmos.Controlador;

import com.example.practica3algoritmos.Modelo.DiceGame;
import com.example.practica3algoritmos.Vista.VentanaJuego;

public class Controlador {
    private DiceGame juego;
    private VentanaJuego ventana;
    // Para diferenciar si debemos lanzar o mover personas
    private boolean debeLanzar;

    public Controlador(DiceGame juego, VentanaJuego ventana) {
        this.juego = juego;
        this.ventana = ventana;
        debeLanzar = true;

        ventana.alHacerAccion(this::manejarAccionRonda);
        ventana.alReiniciarPartida(this::manejarReinicio);
    }

    private void manejarAccionRonda() {
        System.out.println(juego.getRondaActual());
        if (debeLanzar) {
            juego.lanzarDados();
            ventana.getSeccionJugadores().redibujarSeccion();
            ventana.cambiarTextoBotonRonda("Mover personas");
            ventana.actualizarLabelRonda(juego.getRondaActual());
            debeLanzar = false;
        } else {
            juego.moverPersonas();
            ventana.getSeccionJugadores().redibujarSeccion();
            ventana.actualizarLabelRonda(juego.getRondaActual());

            if (juego.getRondaActual() >= 20) {
                ventana.cambiarVisibilidadBotonRonda(false);
                ventana.cambiarVisibilidadBotonReiniciar(true);
            } else {

                ventana.cambiarTextoBotonRonda("Tirar dados");
                debeLanzar = true;
            }


        }

    }

    private void manejarReinicio() {
        juego.reiniciarJuego();
        ventana.getSeccionJugadores().redibujarSeccion();
        ventana.actualizarLabelRonda(juego.getRondaActual());

        debeLanzar = true;
        ventana.cambiarTextoBotonRonda("Tirar dados");
        ventana.cambiarVisibilidadBotonRonda(true);
        ventana.cambiarVisibilidadBotonReiniciar(false); // corregido
    }
}
