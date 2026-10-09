package com.example.practica3algoritmos.Vista.objetosGUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PersonaGUI extends ImageView {

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


    public PersonaGUI(int tamanoFicha, TipoFicha tipo) {
        setFitHeight(tamanoFicha);
        setFitWidth(tamanoFicha);
        setPreserveRatio(true);
        cargarImagen(tipo);
    }

    private void cargarImagen(TipoFicha tipo) {
        String rutaPlaceholder = "/recursos/iconos/placeholder.png";
        try {
            setImage(new Image(getClass().getResource(tipo.getRuta()).toExternalForm()));
        } catch (RuntimeException e) {
            try {
                setImage(new Image(getClass().getResource(tipo.getRuta()).toExternalForm()));
            } catch (RuntimeException ex) {
                System.out.println("No se pudo cargar ninguna imagen, revisa las rutas o la imagen");
            }
        }
    }

}
