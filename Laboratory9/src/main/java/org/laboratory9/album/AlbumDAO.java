package org.laboratory9.album;

import java.util.List;

public interface AlbumDAO {

    void createAlbum(Album album);

    void updateAlbum(Album album, int id);

    void deleteAlbum(int id);

    void printAllAlbums();

    void reset();

    List<Album> getAllAlbums();

    /// CRUD - Create, Retrieve, Update, Delete

}
