package org.laboratory9.album;

import org.laboratory9.artist.Artist;

public interface AlbumRepository {

    void create(Album album);

    Album findById(int id);

    Album findByName(String name);

}
