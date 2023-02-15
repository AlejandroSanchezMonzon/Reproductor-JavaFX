package reproductorjavafx.models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Multimedia {
    private final Media media;
    private final MediaPlayer mediaPlayer;

    public Multimedia(String mp3) {
        media = new Media("https://192.168.16.10/" + mp3);
        mediaPlayer = new MediaPlayer(media);
    }

    public Media getMedia() {
        return media;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
