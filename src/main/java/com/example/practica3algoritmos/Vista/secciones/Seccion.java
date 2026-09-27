package com.example.practica3algoritmos.Vista.secciones;

public abstract class Seccion {

    public void redibujarSeccion() {
        System.out.println("Redibujando seccion");
    }

    protected void crearSeccion() {
        System.out.println("Creando sección");
    }
}
