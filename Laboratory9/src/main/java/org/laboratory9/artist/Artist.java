package org.laboratory9.artist;

import jakarta.persistence.*;
import org.laboratory9.album.Album;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
@NamedNativeQuery(name = "Artist.selectAll", query = "Select * from artist", resultClass = Artist.class)
@NamedNativeQuery(name = "Artist.findById", query = "Select * from artist as a where a.id = :id", resultClass = Artist.class)
@NamedNativeQuery(name = "Artist.findByName", query = "Select * from artist as a where a.name = :name", resultClass = Artist.class)
@NamedNativeQuery(name = "Artist.getAlbums", query = "SELECT al.id, al.title, al.release_year, al.artist_id from album as al\n" +
                                                       "INNER JOIN artist as ar ON al.artist_id = ar.id\n" +
                                                        "WHERE al.artist_id = :id", resultClass = Album.class)
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private String birthDate;

////    @OneToMany(mappedBy = "albumArtist", orphanRemoval = true)
//    private List<Album> albumList = null;

//    public List<Album> getAlbumList() {
//        if(albumList == null)
//            albumList = new ArrayList<>();
//        return albumList;
//    }

//    public void setAlbumList(List<Album> albumList) {
//        this.albumList = albumList;
//    }

    public Artist() {};

    public Artist(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }

    public void replace(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

}
