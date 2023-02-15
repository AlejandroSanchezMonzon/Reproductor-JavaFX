package reproductorjavafx.models;

public class AlbumRead {
    private String title;
    private int year;
    private String picture;
    private String mbid;
    private int artist_id;
    private int id;
    private ArtistRead artist;

    public AlbumRead(String title, int year, String picture, String mbid, int artist_id, int id, ArtistRead artist) {
        this.title = title;
        this.year = year;
        this.picture = picture;
        this.mbid = mbid;
        this.artist_id = artist_id;
        this.id = id;
        this.artist = artist;
    }

    public AlbumRead() {
    }

    @Override
    public String toString() {
        return "AlbumRead{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", picture='" + picture + '\'' +
                ", mbid='" + mbid + '\'' +
                ", artist_id=" + artist_id +
                ", id=" + id +
                ", artist=" + artist +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArtistRead getArtist() {
        return artist;
    }

    public void setArtist(ArtistRead artist) {
        this.artist = artist;
    }
}
