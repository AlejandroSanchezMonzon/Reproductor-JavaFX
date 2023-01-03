package reproductorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale location = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("reproductorjavafx/international/strings", location);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/app-main.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), 440, 700);

        stage.setTitle("Reproductor JavaFXML.");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icons/app-icon.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}