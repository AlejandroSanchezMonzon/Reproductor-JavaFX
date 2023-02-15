package reproductorjavafx.models;

public class ArtistRead {
    private String name;
    private String mbid;
    private String artist_background;
    private String artist_logo;
    private String artist_thumbnail;
    private String artist_banner;
    private int id;

    public ArtistRead(String name, String mbid, String artist_background, String artist_logo, String artist_thumbnail, String artist_banner, int id) {
        this.name = name;
        this.mbid = mbid;
        this.artist_background = artist_background;
        this.artist_logo = artist_logo;
        this.artist_thumbnail = artist_thumbnail;
        this.artist_banner = artist_banner;
        this.id = id;
    }

    public ArtistRead() {
    }

    @Override
    public String toString() {
        return "ArtistRead{" +
                "name='" + name + '\'' +
                ", mbid='" + mbid + '\'' +
                ", artist_background='" + artist_background + '\'' +
                ", artist_logo='" + artist_logo + '\'' +
                ", artist_thumbnail='" + artist_thumbnail + '\'' +
                ", artist_banner='" + artist_banner + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getArtist_background() {
        return artist_background;
    }

    public void setArtist_background(String artist_background) {
        this.artist_background = artist_background;
    }

    public String getArtist_logo() {
        return artist_logo;
    }

    public void setArtist_logo(String artist_logo) {
        this.artist_logo = artist_logo;
    }

    public String getArtist_thumbnail() {
        return artist_thumbnail;
    }

    public void setArtist_thumbnail(String artist_thumbnail) {
        this.artist_thumbnail = artist_thumbnail;
    }

    public String getArtist_banner() {
        return artist_banner;
    }

    public void setArtist_banner(String artist_banner) {
        this.artist_banner = artist_banner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
