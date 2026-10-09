package com.example.practica3algoritmos.Vista.objetosGUI;

import com.example.practica3algoritmos.Modelo.Dado;
import javafx.geometry.Insets;
import javafx.scene.Cursor;

public class DadoGUI extends ImageButton {
    private Dado dadoAMostrar;
    private String ruta;

    public DadoGUI(int tamanoDado, Dado dadoAMostrar) {
        String rutaPlaceholder = "/recursos/iconos/placeholder.png";
        super(rutaPlaceholder, tamanoDado, tamanoDado);
        setPadding(Insets.EMPTY);
        this.dadoAMostrar = dadoAMostrar;
        setCursor(Cursor.DEFAULT);
        actualizar();
    }

    public void actualizar() {
        ruta = "/recursos/iconos/dado/dado_" + dadoAMostrar.getValor() + ".png";
        String rutaPlaceholder = "/recursos/iconos/placeholder.png";

        try {
            cambiarImagen(ruta);
        } catch (Exception e) {
            try {
                cambiarImagen(rutaPlaceholder);
            } catch (Exception ex) {
                System.out.println("No se pudo generar la imagen del dado por que \n" +
                        e.getMessage());
            }
        }
        setCursor(Cursor.DEFAULT);
    }

    public void alSeleccionarDado(Runnable accion) {
        setOnAction(e -> accion.run());
    }

    public Dado getDadoAMostrar() {
        return dadoAMostrar;
    }

    public void iluminarDado(boolean haSidoSeleccionado) {
        if (haSidoSeleccionado) {
            if (!getStyleClass().contains("dado-seleccionado")) getStyleClass().add("dado-seleccionado");
        } else {
            getStyleClass().remove("dado-seleccionado");
        }
    }

}
