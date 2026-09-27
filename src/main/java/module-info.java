module com.example.practica3algoritmos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.example.practica3algoritmos to javafx.fxml;
    exports com.example.practica3algoritmos;
}