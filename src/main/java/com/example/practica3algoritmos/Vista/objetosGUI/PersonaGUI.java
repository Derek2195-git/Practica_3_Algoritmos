package com.example.practica3algoritmos.Vista.objetosGUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PersonaGUI extends ImageView {

    public PersonaGUI(int tamanoFicha, TipoFicha tipo) {
        setFitHeight(tamanoFicha);
        setFitWidth(tamanoFicha);
        setPreserveRatio(true);
        cargarImagen(tipo);
    }

    private void cargarImagen(TipoFicha tipo) {
        try {
            setImage(new Image(getClass().getResource(tipo.getRuta()).toExternalForm()));
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar ninguna imagen, revisa las rutas o la imagen");
        }
    }

}
