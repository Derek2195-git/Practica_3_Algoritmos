package com.example.practica3algoritmos.Vista;

import com.example.practica3algoritmos.Modelo.Dado;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DadoGUI extends ImageView {
    private Dado dadoAMostrar;

    public DadoGUI(int tamanoDado, Dado dadoAMostrar) {
        this.dadoAMostrar = dadoAMostrar;
        setFitHeight(tamanoDado);
        setFitWidth(tamanoDado);
        setPreserveRatio(true);
        actualizar();
    }

    public void actualizar() {
        String ruta = "/iconos/dado/dado_" + dadoAMostrar.getValor() + ".png";
        String rutaPlaceholder = "/iconos/placeholder.png";
        Image imagenDado;
        try {
            imagenDado = new Image(getClass().getResource(ruta).toExternalForm());
            setImage(imagenDado);
        } catch (RuntimeException e) {
            imagenDado = new Image(getClass().getResource(rutaPlaceholder).toExternalForm());
            setImage(imagenDado);
        }
    }
}
