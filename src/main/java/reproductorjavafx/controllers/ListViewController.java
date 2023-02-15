package reproductorjavafx.controllers;

import javafx.application.Platform;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;
import reproductorjavafx.App;
import reproductorjavafx.models.AlbumRead;
import reproductorjavafx.models.ArtistRead;
import reproductorjavafx.models.Multimedia;
import reproductorjavafx.models.SongRead;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ListViewController implements Initializable {
    @FXML
    public ListView<SongRead> songsList;
    @FXML
    public FlowPane playerPane = new FlowPane();

    @FXML
    public ImageView songImage;

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
    private Slider timeSlider;

    private boolean isPause = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getSongs();

        songsList.setOnMouseClicked(event -> {
            SongRead song = songsList.getSelectionModel().getSelectedItem();
            songsList.setVisible(false);

            System.out.println("-> Canción seleccionada: " + song.toString());

            setUpReproducer(song);

            playerPane.setVisible(true);
        });
    }

    private void getSongs() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.16.10/songs/"))
                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        response.thenAccept(res -> {
            if (res.statusCode() == 200) {
                JSONArray dataArray = new JSONArray(res.body());
                Platform.runLater(() -> createSongsAlternative(dataArray));

                System.out.println(">>> Success.");
            }
        });
    }

/*
    private void createSongs(JSONArray dataArray) {
        dataArray.forEach(data -> songsList.getItems().add(new SongRead(
                ((JSONObject) data).optString("title"),
                ((JSONObject) data).getString("publisher"),
                ((JSONObject) data).getInt("year"),
                ((JSONObject) data).getInt("track_num"),
                ((JSONObject) data).getString("file"),
                ((JSONObject) data).getInt("album_id"),
                ((JSONObject) data).getInt("genre_id"),
                ((JSONObject) data).getInt("id"),
                new AlbumRead(
                        ((JSONObject) data).getJSONObject("album").getString("title"),
                        ((JSONObject) data).getJSONObject("album").getInt("year"),
                        ((JSONObject) data).getJSONObject("album").getString("picture"),
                        ((JSONObject) data).getJSONObject("album").getString("mbid"),
                        ((JSONObject) data).getJSONObject("album").getInt("artist_id"),
                        ((JSONObject) data).getJSONObject("album").getInt("id"),
                        new ArtistRead(
                                ((JSONObject) data).getJSONObject("album").getJSONObject("artist").getString("name"),
                                ((JSONObject) data).getJSONObject("album").getJSONObject("artist").getString("mbid"),
                                ((JSONObject) data).getJSONObject("album").getJSONObject("artist").getString("artist_background"),
                                ((JSONObject) data).getJSONObject("album").getJSONObject("artist").getString("artist_logo"),
                                ((JSONObject) data).getJSONObject("album").getJSONObject("artist").getString("artist_thumbnail"),
                                ((JSONObject) data).getJSONObject("album").getJSONObject("artist").getString("artist_banner"),
                                ((JSONObject) data).getJSONObject("album").getJSONObject("artist").getInt("id")
                        )
                )
        )));
    }
*/

    /**
     * He decidido emplear este método para hacerlo porque con el método de arriba me daba problemas ya que hay datos nulos.
     * He leido que la librería de JSONArray tiene el método opt[tipo] con el que podemos controlar eso para que no de problemas.
     */
    private void createSongsAlternative(JSONArray dataArray) {
        dataArray.forEach(data -> {
            JSONObject json = (JSONObject) data;
            JSONObject albumJson = json.optJSONObject("album");
            JSONObject artistJson = albumJson.optJSONObject("artist");

            songsList.getItems().add(new SongRead(
                    json.optString("title"),
                    json.optString("publisher"),
                    json.optInt("year"),
                    json.optInt("track_num"),
                    json.optString("file"),
                    json.optInt("album_id"),
                    json.optInt("genre_id"),
                    json.optInt("id"),
                    new AlbumRead(albumJson.optString("title"),
                            albumJson.optInt("year"),
                            albumJson.optString("picture"),
                            albumJson.optString("mbid"),
                            albumJson.optInt("artist_id"),
                            albumJson.optInt("id"),
                            new ArtistRead(
                                    artistJson.optString("name"),
                                    artistJson.optString("mbid"),
                                    artistJson.optString("artist_background"),
                                    artistJson.optString("artist_logo"),
                                    artistJson.optString("artist_thumbnail"),
                                    artistJson.optString("artist_banner"),
                                    artistJson.optInt("id"))
                    )
            ));
        });
    }

    public void setUpReproducer(SongRead song) {
        Multimedia multimedia = new Multimedia(song.getFile());

        multimedia.getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ?> change) -> {
            String key = change.getKey();
            if ("image".equals(key)) {
                songImage.setImage((Image) change.getValueAdded());
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
        Descomentar este bloque de código y comentar el de arriba para que la cancion cambie al desplazar y soltar el slider
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