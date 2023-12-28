package fr.vocaltech.spring.webscraper.models;

import jakarta.persistence.*;

@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String artistName;

    private String albumTitle;

    private String songTitle;

    private String imgUrl;

    private String imgText;

    // constructors
    protected Music() {}

    public Music(String artistName, String albumTitle, String songTitle, String imgUrl, String imgText) {
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.songTitle = songTitle;
        this.imgUrl = imgUrl;
        this.imgText = imgText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgText() {
        return imgText;
    }

    public void setImgText(String imgText) {
        this.imgText = imgText;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", artistName='" + artistName + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgText='" + imgText + '\'' +
                '}';
    }
}
