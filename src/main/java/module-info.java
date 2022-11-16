module com.example.reproductorjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens reproductorjavafx to javafx.fxml;
    exports reproductorjavafx;
    exports reproductorjavafx.controllers;
    opens reproductorjavafx.controllers to javafx.fxml;
}