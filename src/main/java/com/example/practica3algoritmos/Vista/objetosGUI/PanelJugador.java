package com.example.practica3algoritmos.Vista.objetosGUI;

import com.example.practica3algoritmos.Modelo.Dado;
import com.example.practica3algoritmos.Modelo.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.function.Consumer;

public class PanelJugador {
    private final int ALTO_ICONO = 64;
    private final int ANCHO_ICONO = 64;
    private final int TAMANO_DADO = 24;
    private final int TAMANO_PERSONA = 8;
    private final int DADOS_POR_FILA = 5;
    private final int ESPACIO_DADOS = 2;

    private Jugador jugador;
    private FlowPane contenedorDados;
    private ArrayList<DadoGUI> dados = new ArrayList<>();
    private Dado dadoSeleccionado;
    private Consumer<Dado> accionSeleccionarDado;
    //private DadoGUI dado;
    private VBox contenedor;
    private FlowPane contenedorPersonas;

    public PanelJugador(Jugador jugador) {
        this.jugador = jugador;

        String rutaIcono = "/recursos/iconos/iconoJugador.png";
        ImageView icono;

        icono = new ImageView(new Image(getClass().getResource(rutaIcono).toExternalForm()));

        Label labelJugador = new Label("Jugador " + jugador.getNumero());
        contenedorDados = new FlowPane(ESPACIO_DADOS, ESPACIO_DADOS);
        contenedorDados.setAlignment(Pos.CENTER);
        contenedorDados.setPrefWrapLength(DADOS_POR_FILA * TAMANO_DADO
                + (DADOS_POR_FILA - 1) * ESPACIO_DADOS);

        contenedorPersonas = new FlowPane();
        contenedorPersonas.setHgap(2);
        contenedorPersonas.setVgap(2);
        contenedorPersonas.setPrefWrapLength(64);

        icono.setFitHeight(ALTO_ICONO);
        icono.setFitWidth(ANCHO_ICONO);
        icono.setPreserveRatio(true);


        contenedor = new VBox(4, labelJugador, icono, contenedorDados, contenedorPersonas);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getStyleClass().add("panel-jugador");
        redibujarDados();
    }

    public void redibujar() {
        redibujarDados();
        contenedorPersonas.getChildren().clear();

        int cantidadPersonas = jugador.getColaPersonas().tamanoCola();
        int cantidadPersonasMovidas = jugador.getPersonasMovidasEnRondaAnterior();

        for (int i = 0; i < cantidadPersonas; i++) {
            // Verificamos si la persona a dibujar fue de las que se movieron
            boolean esRecienMovida = (cantidadPersonas - i) <= cantidadPersonasMovidas;
            contenedorPersonas.getChildren().add(
                    new PersonaGUI(TAMANO_PERSONA, esRecienMovida)
            );
        }
    }

    public void redibujarDados() {
        contenedorDados.getChildren().clear();
        dados.clear();

        if (jugador.getDadosActuales().isEmpty()) {
            contenedorDados.getChildren().add(crearRanuraVacia());
            return;
        }

        for (Dado d : jugador.getDadosActuales()) {
            DadoGUI dado = new DadoGUI(TAMANO_DADO, d);
            dado.iluminarDado(d == dadoSeleccionado);
            dado.alSeleccionarDado(() -> {
                if (accionSeleccionarDado != null) accionSeleccionarDado.accept(d);
            });
            dados.add(dado);
            contenedorDados.getChildren().add(dado);
        }
    }

    public Node crearRanuraVacia() {
        Region ranura = new Region();
        ranura.setPrefSize(TAMANO_DADO, TAMANO_DADO);
        ranura.getStyleClass().add("ranura-dado");
        ranura.setOnMouseClicked(e-> {
            if (accionSeleccionarDado != null) accionSeleccionarDado.accept(null);
        });
        return ranura;
    }

    public void alSeleccionarDado(Consumer<Dado> accionSeleccionarDado) {
        this.accionSeleccionarDado = accionSeleccionarDado;
    }

    public void resaltarDado(Dado dado) {
        dadoSeleccionado = dado;
        for (DadoGUI d : dados) {
            d.iluminarDado(d.getDadoAMostrar() == dado);
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public VBox getContenedor() { return contenedor; }
}
