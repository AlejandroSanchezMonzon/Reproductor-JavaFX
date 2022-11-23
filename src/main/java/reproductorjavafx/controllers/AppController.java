package reproductorjavafx.controllers;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import reproductorjavafx.models.Multimedia;

import java.io.File;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> songs = new ArrayList<>();
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/BoDleasons_-_Forward_to_the_Future.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/DHDMusic_-_Contemporary_Covers.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/Flashinmusic_-_O_menino_da_percussao.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/Heavenless_-_Motivational_Business_corporate.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/Igor_Pumphonia_-_Igor_Pumphonia_-_My_Love_Is_Real__Second_Version_Dub_.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/NEONMusic_-_Positive_Progressive_House.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/QubeSounds_-_Digital_Abstract_Technology_Promo.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/QubeSounds_-_The_Vlog_Trip_Hop_Beat.mp3")));
        songs.add(String.valueOf(this.getClass().getResource("reproductorjavafx/audios/TimTaj_-_Just_Go_Away.mp3")));

        for (int i = 0; i < songs.size(); i++) {
            media = new Media(songs.get(i));

            media.getMetadata().addListener(MapChangeListener.Change < ? extends String, ?>change) ->{
                switch (change.getKey().toString()) {
                    case "title" -> songLabel.setText(change.getValue().toString());
                    case "album" ->
                }
            };
        }
    }
}