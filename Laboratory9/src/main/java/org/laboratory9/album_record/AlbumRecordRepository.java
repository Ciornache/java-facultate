package org.laboratory9.album_record;

import org.laboratory9.album.Album;
import org.laboratory9.genre.Genre;

import java.util.ArrayList;
import java.util.List;

public interface AlbumRecordRepository {

    void create(Album album, Genre genre);

    AlbumRecord findById(int id);

    List<Album> findAlbumsByGenre(Genre genre);

    List<Genre> findGenresByAlbums(Album album);

    void reset();

}
