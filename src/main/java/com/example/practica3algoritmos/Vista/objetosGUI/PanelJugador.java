package com.example.practica3algoritmos.Vista.objetosGUI;

import com.example.practica3algoritmos.Modelo.Jugador;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class PanelJugador {
    private final int ALTO_ICONO = 60;
    private final int ANCHO_ICONO = 60;
    private final int TAMANO_DADO = 32;
    private final int TAMANO_PERSONA = 16;

    private Jugador jugador;
    private DadoGUI dado;
    private VBox contenedor;
    private FlowPane contenedorPersonas;

    public PanelJugador(Jugador jugador) {
        this.jugador = jugador;

        String rutaIcono = "/recursos/iconos/iconoJugador.png";
        String rutaPlaceholder = "/recursos/iconos/placeholder.png";
        ImageView icono;

        icono = new ImageView(new Image(getClass().getResource(rutaPlaceholder).toExternalForm()));

        Label labelJugador = new Label("Jugador " + jugador.getNumero());
        dado = new DadoGUI(TAMANO_DADO, jugador.getDadoJugador());

        contenedorPersonas = new FlowPane();
        contenedorPersonas.setHgap(2);
        contenedorPersonas.setVgap(2);
        contenedorPersonas.setPrefWrapLength(TAMANO_DADO * 2);

        icono.setFitHeight(ALTO_ICONO);
        icono.setFitWidth(ANCHO_ICONO);
        icono.setPreserveRatio(true);


        contenedor = new VBox(4, labelJugador, icono, dado, contenedorPersonas);
        contenedor.setAlignment(Pos.CENTER);
    }

    public void redibujar() {
        dado.actualizar();
        contenedorPersonas.getChildren().clear();

        int cantidadPersonas = jugador.getColaPersonas().tamanoCola();
        for (int i = 0; i < cantidadPersonas; i++) {
            contenedorPersonas.getChildren().add(
                    new PersonaGUI(TAMANO_PERSONA)
            );
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public VBox getContenedor() { return contenedor; }
}
