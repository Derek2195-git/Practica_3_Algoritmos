package com.example.practica3algoritmos.Controlador;

import com.example.practica3algoritmos.Modelo.DiceGame;
import com.example.practica3algoritmos.Modelo.Jugador;
import com.example.practica3algoritmos.Modelo.Persona;

public class Controlador {
    // Haremos una versión del DiceGame para terminal solo para comprobar que este funcionando

    public static void main() {
        DiceGame juego = new DiceGame(5);
        for (int i = 1; i <= 20; i++) {
            juego.empezarRonda();

            System.out.println("Ronda " + i);
            juego.getJugadores().forEach(System.out::println);
            System.out.println("Throughput: " + juego.calcularThroughput());
            System.out.println("Personas en el sistema: " + juego.calcularPersonasEnElSistema());
            System.out.println("\n");
        }
        System.out.println("Personas que llegaron hasta el final: ");
        for (Persona p : juego.getPersonasSalidas()) {
            int tiempoSistema = p.getRondaSalida() - p.getRondaEntrada();
            if (!(tiempoSistema <= 0)) System.out.println(p + "\n Tiempo en el sistema: " + tiempoSistema);
        }
    }
}
