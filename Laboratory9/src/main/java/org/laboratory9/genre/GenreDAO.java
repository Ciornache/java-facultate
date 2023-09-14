package org.laboratory9.genre;

import java.util.List;

public interface GenreDAO {

    void createGenre(Genre genre);

    void updateGenre(Genre genre, int id);

    void deleteGenre(int id);

    void printAllGenres();

    void reset();

    List<Genre> getAllGenres();

    /// CRUD - Create, Retrieve, Update, Delete

}
