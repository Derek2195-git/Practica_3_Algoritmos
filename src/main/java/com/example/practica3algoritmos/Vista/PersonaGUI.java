package com.example.practica3algoritmos.Vista;

import com.example.practica3algoritmos.Modelo.Persona;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PersonaGUI extends ImageView {
    private Persona personaAMostrar;

    public PersonaGUI(int tamanoFicha, Persona personaAMostrar) {
        this.personaAMostrar = personaAMostrar;
        setFitHeight(tamanoFicha);
        setFitWidth(tamanoFicha);
        setPreserveRatio(true);
        cargarImagen();
    }

    public PersonaGUI(int tamanoFicha) {
        setFitHeight(tamanoFicha);
        setFitWidth(tamanoFicha);
        setPreserveRatio(true);
        cargarImagen();
    }

    private void cargarImagen() {
        String ruta = "/iconos/fichaPersona.png";
        String rutaPlaceholder = "/iconos/placeholder.png";
        Image imagenPersona;
        try {
            imagenPersona = new Image(getClass().getResource(ruta).toExternalForm());
            setImage(imagenPersona);
        } catch (RuntimeException e) {
            imagenPersona = new Image(getClass().getResource(rutaPlaceholder).toExternalForm());
            setImage(imagenPersona);
        }
    }

    public Persona getPersonaAMostrar() {
        return personaAMostrar;
    }
}
