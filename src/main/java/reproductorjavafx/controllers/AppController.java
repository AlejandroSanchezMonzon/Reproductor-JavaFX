package reproductorjavafx.controllers;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import reproductorjavafx.models.Multimedia;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    Media media;
    MediaPlayer mediaPlayer;

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

    public AppController(Button startButton, Button pauseButton, Button endButton, Label songLabel, Label albumLabel, Label artistLabel, ImageView songImage, Slider timeSlider) {
        this.startButton = startButton;
        this.pauseButton = pauseButton;
        this.endButton = endButton;
        this.songLabel = songLabel;
        this.albumLabel = albumLabel;
        this.artistLabel = artistLabel;
        this.songImage = songImage;
        this.timeSlider = timeSlider;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Multimedia> songs = new ArrayList<>();
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/BoDleasons_-_Forward_to_the_Future.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/DHDMusic_-_Contemporary_Covers.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/Flashinmusic_-_O_menino_da_percussao.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/Heavenless_-_Motivational_Business_corporate.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/Igor_Pumphonia_-_Igor_Pumphonia_-_My_Love_Is_Real__Second_Version_Dub_.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/NEONMusic_-_Positive_Progressive_House.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/QubeSounds_-_Digital_Abstract_Technology_Promo.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/QubeSounds_-_The_Vlog_Trip_Hop_Beat.mp3"))));
        songs.add(new Multimedia(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/TimTaj_-_Just_Go_Away.mp3"))));

        songs.get(0).getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ?>change) -> {
            switch (change.getKey().toString()) {
                case "title" -> songLabel.setText(change.getValueAdded().toString());
                case "album" -> albumLabel.setText(change.getValueAdded().toString());
                case "artist" -> artistLabel.setText(change.getValueAdded().toString());
                case "image" -> songImage.setImage((Image) change.getValueAdded());
            }
        });

        sliderDuration(songs.get(0));
        sliderController(songs.get(0));
    }

    private void sliderDuration(Multimedia multimedia) {
        multimedia.getMediaPlayer().currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!timeSlider.isValueChanging()) {
                timeSlider.setValue(newTime.toSeconds());
            }
        });
    }

    private void sliderController(Multimedia multimedia) {
        timeSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                multimedia.getMediaPlayer().seek(Duration.seconds(newValue.floatValue())));
    }

    private void changeButton(Button button, String path){
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25.0);
        imageView.setFitWidth(25.0);
        button.setGraphic(imageView);
    }
}