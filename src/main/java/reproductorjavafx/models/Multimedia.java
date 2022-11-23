package reproductorjavafx.models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Multimedia {
    private File archivo;
    private Media media;
    private MediaPlayer mediaPlayer;

    public Multimedia(String mp3) {
        archivo = new File(mp3);
        media = new Media(archivo.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public Media getMedia() {
        return media;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
