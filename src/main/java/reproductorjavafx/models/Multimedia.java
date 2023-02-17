package reproductorjavafx.models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Multimedia {
    private final Media media;
    private final MediaPlayer mediaPlayer;

    // Clase -> http://192.168.16.10
    // Casa -> http://localhost:8080
    public Multimedia(String mp3) {
        media = new Media("http://192.168.16.10" + mp3);
        mediaPlayer = new MediaPlayer(media);
    }

    public Media getMedia() {
        return media;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
