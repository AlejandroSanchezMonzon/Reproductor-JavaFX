package reproductorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/app-main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 700);
        stage.setTitle("Reproductor de música.");
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icons/app-icon.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}