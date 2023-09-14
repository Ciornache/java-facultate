package org.laboratory9.genre;

import jakarta.persistence.*;
import org.laboratory9.album.Album;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "genre")
@NamedNativeQuery(name = "Genre.selectAll", query = "Select * from genre", resultClass = Genre.class)
@NamedNativeQuery(name = "Genre.findById", query = "Select * from genre as a where a.id = :id", resultClass = Genre.class)
@NamedNativeQuery(name = "Genre.findByName", query = "Select * from genre as a where a.name = :name", resultClass = Genre.class)
@NamedNativeQuery(name = "Genre.reset", query = "Delete from genre")
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Genre() {};

    public Genre(String name) {
        this.name = name;
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

    public void replace(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
