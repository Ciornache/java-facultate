package org.laboratory9.genre;

import org.laboratory9.album.Album;

import java.util.List;

public interface GenreRepository {
    void create(Genre genre);

    Genre findGenreById(int id);

    Genre findGenreByName(String name);

    List<Album> getAlbums(Genre Genre);

}
