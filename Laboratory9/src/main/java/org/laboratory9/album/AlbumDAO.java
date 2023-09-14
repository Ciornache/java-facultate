package org.laboratory9.album;

public interface AlbumDAO {

    void createAlbum(Album album);

    void updateAlbum(Album album, int id);

    void deleteAlbum(int id);

    void printAllAlbums();

    /// CRUD - Create, Retrieve, Update, Delete

}
