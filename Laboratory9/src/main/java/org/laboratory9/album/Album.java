package org.laboratory9.album;

import jakarta.persistence.*;
import org.laboratory9.artist.Artist;

import java.io.Serializable;

@Entity
@Table(name = "album")
@NamedNativeQuery(name = "Album.selectAll", query = "Select * from album", resultClass = Album.class)
@NamedNativeQuery(name = "Album.findById", query = "Select * from album as a where a.id = :id", resultClass = Album.class)
@NamedNativeQuery(name = "Album.findByTitle", query = "Select * from album as a where a.title = :title", resultClass = Album.class)
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String name;

    @Column(name = "release_year")
    private String releaseYear;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist albumArtist;

    public Album() {};

    public Album(String name, String releaseYear, Artist artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.albumArtist = artist;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return albumArtist;
    }

    public void setArtist(Artist artist) {
        this.albumArtist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void replace(Album album) {
        this.name = album.getName();
        this.releaseYear = album.getReleaseYear();
        this.albumArtist = album.getArtist();
    }
    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", artist=" + albumArtist +
                '}';
    }
}
