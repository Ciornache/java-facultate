package org.laboratory9.artist;

public interface ArtistDAO {

    void createArtist(Artist artist);

    void updateArtist(Artist artist, int id);

    void deleteArtist(int id);

    void printAllArtists();

    /// CRUD - Create, Retrieve, Update, Delete

}
