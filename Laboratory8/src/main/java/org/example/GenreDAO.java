package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {

    private final List<Genre> genreList = new ArrayList<>();
    Database database = Database.getInstance();
    private static GenreDAO instance = null;

    public void updateArtist(int id, Genre genre) throws SQLException, ClassNotFoundException {
        Genre genre2 = findGenreById(id);
        genre.setGenre_id(genre2.getGenre_id());
        removeGenre(genre2);
        addGenre(genre);
        genreList.remove(genre2);
        genreList.add(genre);
    }

    public void removeGenre(Genre genre) throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        try {
            Statement st = connection.createStatement();
            String sqlStatament = String.format("DELETE FROM genre where genre.genre_id = %2d;", genre.getGenre_id());
            st.executeUpdate(sqlStatament);
            genreList.remove(genre);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addGenre(Genre genre) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlInsert = "INSERT INTO genre VALUES(" + genre.insertForm() + ");";
//            System.out.println(sqlInsert);
            st.executeUpdate(sqlInsert);
            genreList.add(genre);
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void printAllGenres() {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();

            String sqlStatament = "SELECT * from genre;";
            ResultSet resultSet = st.executeQuery(sqlStatament);
            while (resultSet.next()) {

                StringBuilder artist = new StringBuilder();
                for(int i = 0;i < 2; ++i)
                    artist.append(resultSet.getString(i + 1)).append(" ");
                System.out.println(artist);
            }
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private GenreDAO() {}

    public static GenreDAO getInstance() {
        if(instance == null)
            instance = new GenreDAO();
        return instance;
    }

    public int getMaximumPrimaryKey() throws SQLException, ClassNotFoundException {

        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();

            String sqlStatament = "SELECT MAX(genre_id) from genre;";
            ResultSet resultSet = st.executeQuery(sqlStatament);
            resultSet.next();
            int value = resultSet.getInt(1);
            connection.close();
            return value;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Genre findGenreById(int id) {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatament = String.format("SELECT * from genre WHERE artists.genre_id = %2d;", id);
            ResultSet resultSet = st.executeQuery(sqlStatament);
            resultSet.next();
            Genre genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
            connection.close();
            return genre;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Genre findGenreByName(String name) {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatament = String.format("SELECT * from genre WHERE genre.name = %s;", name);
            ResultSet resultSet = st.executeQuery(sqlStatament);
            resultSet.next();
            Genre genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
            connection.close();
            return genre;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public Integer findGenreByName(StringBuilder name) throws InvalidGenreException {
        for(Genre genr : genreList) {
            if(genr.getName().contentEquals(name))
                return genr.getGenre_id();
        }
        throw new InvalidGenreException("Invalid Genre. Not found in the database");
    }


    public void deleteAll() {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("Delete from genre;");
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
