package reproductorjavafx.controllers;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import reproductorjavafx.App;
import reproductorjavafx.models.Multimedia;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button endButton;

    @FXML
    private Label songLabel;
    @FXML
    private Label albumLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private ImageView songImage;
    @FXML
    private Slider timeSlider;

    private boolean isPause = true;
    private final String resourcesPath =
            System.getProperty("user.dir") + File.separator +
                    "src" + File.separator +
                    "main" + File.separator +
                    "resources" + File.separator +
                    "reproductorjavafx";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Multimedia multimedia = new Multimedia(resourcesPath + File.separator + "audios" + File.separator + "Paulo_Londra_Julieta.mp3");

        multimedia.getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ?> change) -> {
            String key = change.getKey();
            if ("image".equals(key)) {
                songImage.setImage((Image) change.getValueAdded());
            } else {
                songImage.setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/covers/julieta_cover.jpg"))));
                songImage.setFitHeight(150.0);
                songImage.setFitWidth(200.0);
            }

            if ("title".equals(key)) {
                songLabel.setText(change.getValueAdded().toString());
            }

            if ("artist".equals(key)) {
                artistLabel.setText(change.getValueAdded().toString());
            }

            if ("album".equals(key)) {
                albumLabel.setText(change.getValueAdded().toString());
            } else {
                albumLabel.setText(songLabel.getText());
            }
        });

        sliderSync(multimedia);
        sliderController(multimedia);

        pauseButton.setOnAction(event -> playController(multimedia));
        startButton.setOnAction(event -> rewindSong(multimedia));
        endButton.setOnAction(event -> finishSong(multimedia));
    }

    private void finishSong(Multimedia multimedia) {
        multimedia.getMediaPlayer().seek(multimedia.getMediaPlayer().getTotalDuration());
        timeSlider.setValue(multimedia.getMediaPlayer().getTotalDuration().toMillis());
    }

    private void rewindSong(Multimedia multimedia) {
        multimedia.getMediaPlayer().seek(Duration.ZERO);
        timeSlider.setValue(Duration.ZERO.toMillis());
    }

    private void playController(Multimedia multimedia) {
        if (isPause) {
            multimedia.getMediaPlayer().play();
            isPause = false;

            changeButton("pause");
        } else {
            multimedia.getMediaPlayer().pause();
            isPause = true;

            changeButton("play");
        }
    }

    private void sliderSync(Multimedia multimedia) {
        multimedia.getMediaPlayer().currentTimeProperty().addListener((obs, oldValue, newValue) -> {
            if (!timeSlider.isValueChanging()) {
                double totalDuration = multimedia.getMediaPlayer().getTotalDuration().toSeconds();

                timeSlider.setValue((newValue.toSeconds() * 100) / totalDuration);
            }
        });
    }

    private void sliderController(Multimedia multimedia) {
        timeSlider.setOnMousePressed(event -> {

            double sliderPos = timeSlider.getValue();
            double totalDuration = multimedia.getMediaPlayer().getTotalDuration().toSeconds();
            double time = (sliderPos * totalDuration) / 100;

            multimedia.getMediaPlayer().seek(Duration.seconds(time));
        });

/*
        Descomentaer este bloque de código y comentar el de arriba para que la cancion cambie al desplazar y soltar el slider
        y no al pulsar en un punto del mismo.

        timeSlider.setOnMouseReleased(event -> {

            double sliderPos = timeSlider.getValue();
            double totalDuration = multimedia.getMediaPlayer().getTotalDuration().toSeconds();
            double time = (sliderPos * totalDuration) / 100;

            multimedia.getMediaPlayer().seek(Duration.seconds(time));
        });
*/
    }

    private void changeButton(String opcion) {
        Image image;
        ImageView imageView;

        if (opcion.equals("play")) {
            image = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icons/play.png")));
        } else {
            image = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icons/pause.png")));
        }

        imageView = new ImageView(image);
        imageView.setFitHeight(25.0);
        imageView.setFitWidth(25.0);
        pauseButton.setGraphic(imageView);
    }
}