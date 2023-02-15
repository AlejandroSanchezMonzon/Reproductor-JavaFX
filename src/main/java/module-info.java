module com.example.reproductorjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.net.http;
    requires org.json;
    requires com.google.gson;


    opens reproductorjavafx to javafx.fxml;
    exports reproductorjavafx;
    exports reproductorjavafx.controllers;
    opens reproductorjavafx.controllers to javafx.fxml;
    exports reproductorjavafx.models;
    opens reproductorjavafx.models to javafx.fxml;
}