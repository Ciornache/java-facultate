package org.laboratory9.genre;

public interface GenreDAO {

    void createGenre(Genre genre);

    void updateGenre(Genre genre, int id);

    void deleteGenre(int id);

    void printAllGenres();

    /// CRUD - Create, Retrieve, Update, Delete

}
