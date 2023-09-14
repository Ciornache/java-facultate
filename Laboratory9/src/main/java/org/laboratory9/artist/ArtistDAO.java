package org.laboratory9.artist;

import java.util.List;

public interface ArtistDAO {

    void createArtist(Artist artist);

    void updateArtist(Artist artist, int id);

    void deleteArtist(int id);

    void printAllArtists();

    List<Artist> getAllArtist();

    void reset();

    /// CRUD - Create, Retrieve, Update, Delete

}
