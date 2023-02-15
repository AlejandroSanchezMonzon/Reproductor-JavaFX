package reproductorjavafx.models;

public class SongRead {
    private String title;
    private String publisher;
    private int year;
    private int track_num;
    private String file;
    private int album_id;
    private int genre_id;
    private int id;
    private AlbumRead album;

    public SongRead(String title, String publisher, int year, int track_num, String file, int album_id, int genre_id, int id, AlbumRead album) {
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.track_num = track_num;
        this.file = file;
        this.album_id = album_id;
        this.genre_id = genre_id;
        this.id = id;
        this.album = album;
    }

    public SongRead() {
    }

    @Override
    public String toString() {
        return title + " / " + album.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTrack_num() {
        return track_num;
    }

    public void setTrack_num(int track_num) {
        this.track_num = track_num;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlbumRead getAlbum() {
        return album;
    }

    public void setAlbum(AlbumRead album) {
        this.album = album;
    }
}
