package org.example;

import java.util.Objects;

public class Album {

    private int album_id = 0;
    private String releaseDateYear = null;
    private String title = null;
    private int artist_id = 0;

    public Album(int album_id, String releaseDateYear, String title, int artist_id) {
        this.album_id = album_id;
        this.releaseDateYear = releaseDateYear;
        this.title = title;
        this.artist_id = artist_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getReleaseDateYear() {
        return releaseDateYear;
    }

    public void setReleaseDateYear(String releaseDateYear) {
        this.releaseDateYear = releaseDateYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album_id=" + album_id +
                ", releaseDateYear='" + releaseDateYear + '\'' +
                ", title='" + title + '\'' +
                ", artist_id=" + artist_id +
                '}';
    }

    public String insertForm() {
        StringBuilder sqlInsertForm = new StringBuilder(Integer.toString(album_id));
        sqlInsertForm.append(',' + "'").append(releaseDateYear).append("'");
        sqlInsertForm.append(',' + "'").append(title).append("'");
        sqlInsertForm.append(',').append(artist_id).append("'");
        return Objects.toString(sqlInsertForm);
    }
}
