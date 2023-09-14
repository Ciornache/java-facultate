package org.laboratory9.artist;

import org.laboratory9.album.Album;

import java.util.List;

public interface ArtistRepository {
    void create(Artist artist);

    Artist findArtistById(int id);

    Artist findArtistByName(String name);

    List<Album> getArtistAlbums(int id);


}
