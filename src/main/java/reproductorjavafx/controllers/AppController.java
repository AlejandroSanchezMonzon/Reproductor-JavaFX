package reproductorjavafx.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    public MenuButton menu;
    @FXML
    public MenuItem songMenuItem;
    @FXML
    public MenuItem settingsMenuItem;
    @FXML
    public MenuItem exitMenuItem;
    @FXML
    public BorderPane songView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        songMenuItem.setOnAction(event -> songView.setVisible(true));
        exitMenuItem.setOnAction(event -> Platform.exit());
    }
}
