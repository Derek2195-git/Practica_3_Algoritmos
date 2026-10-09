package com.example.practica3algoritmos.Vista.objetosGUI;

public enum TipoFicha {
        NORMAL("/recursos/iconos/fichaPersona.png"),
        MOVIDA("/recursos/iconos/fichaPersonaMovida.png"),
        BASE("/recursos/iconos/fichaPersonaBase.png");

        private final String ruta;

        TipoFicha(String ruta) {
            this.ruta = ruta;
        }

        public String getRuta() {
            return ruta;
        }
    }