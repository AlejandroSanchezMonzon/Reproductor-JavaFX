module com.example.reproductorjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens reproductorjavafx to javafx.fxml;
    exports reproductorjavafx;
    exports reproductorjavafx.controllers;
    opens reproductorjavafx.controllers to javafx.fxml;
}