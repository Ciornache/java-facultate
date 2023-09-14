package org.laboratory9.genre;

import org.laboratory9.album.Album;

import java.util.List;

public interface GenreRepository {
    void create(Genre genre);

    Genre findById(int id);

    Genre findByName(String name);

    List<Album> getAlbums(Genre Genre);

}
