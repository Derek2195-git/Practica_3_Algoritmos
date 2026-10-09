package com.example.practica3algoritmos.Vista.objetosGUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PersonaGUI extends ImageView {

    public PersonaGUI(int tamanoFicha, boolean haSidoMovida) {
        setFitHeight(tamanoFicha);
        setFitWidth(tamanoFicha);
        setPreserveRatio(true);
        cargarImagen(haSidoMovida);
    }

    private void cargarImagen(boolean haSidoMovida) {
        String rutaPlaceholder = "/recursos/iconos/placeholder.png";
        String ruta = haSidoMovida ? "/recursos/iconos/fichaPersona.png" : "/recursos/iconos/fichaPersonaMovida.png";
        Image imagenPersona;
        try {
            imagenPersona = new Image(getClass().getResource(ruta).toExternalForm());
            setImage(imagenPersona);
        } catch (RuntimeException e) {
            try {
                imagenPersona = new Image(getClass().getResource(rutaPlaceholder).toExternalForm());
                setImage(imagenPersona);
            } catch (RuntimeException ex) {
                System.out.println("No se pudo cargar ninguna imagen, revisa las rutas o la imagen");
            }
        }
    }

}
