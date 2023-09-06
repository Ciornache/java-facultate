package org.example;

public class Genre {
    private int genre_id = 0;
    private String name = null;

    public Genre(int genre_id, String name)
    {
        this.genre_id = genre_id;
        this.name = name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre_id=" + genre_id +
                ", name='" + name + '\'' +
                '}';
    }

    public String insertForm() {
        return Integer.toString(genre_id) + ',' + "'" + name + "'";
    }
}
