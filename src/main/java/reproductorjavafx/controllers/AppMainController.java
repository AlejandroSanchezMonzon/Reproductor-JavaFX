package reproductorjavafx.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class AppMainController implements Initializable {
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private Button closeButton;
    @FXML
    private Button cancionesButton;

    @FXML
    private ListViewController listViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancionesButton.setOnAction(event -> {
            listViewController.songsList.setVisible(true);
            listViewController.playerPane.setVisible(false);

            System.out.println("Botón de canciones pulsado.");
        });

        exitMenuItem.setOnAction(event -> Platform.exit());
    }
}