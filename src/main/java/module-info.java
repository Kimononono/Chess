module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.example.chess to javafx.fxml;
    exports com.example.chess;
}