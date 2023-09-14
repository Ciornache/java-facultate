package org.laboratory9.album_record;

import jakarta.persistence.*;
import org.laboratory9.album.Album;
import org.laboratory9.album.AlbumRepository;
import org.laboratory9.genre.Genre;

import java.io.Serializable;

@Entity
@Table(name = "album_record")
@NamedNativeQuery(name = "AlbumRecord.findById", query = "Select * from albumrecord where id = :id", resultClass = AlbumRecord.class)
@NamedNativeQuery(name = "AlbumRecord.getAlbumsByGenre", query = "Select * from albumrecord where genre_id = :id", resultClass = AlbumRecord.class)
@NamedNativeQuery(name = "AlbumRecord.getGenresByAlbum", query = "Select * from albumrecord where album_id = :id", resultClass = AlbumRecord.class)
@NamedNativeQuery(name = "AlbumRecord.reset", query = "Delete from album_record")

public class AlbumRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "AlbumRecord{" +
                "id=" + id +
                ", album=" + album +
                ", genre=" + genre +
                '}';
    }

    AlbumRecord() {}; // default

    AlbumRecord(Album album, Genre genre) {
        this.album = album;
        this.genre = genre;
    }
}
