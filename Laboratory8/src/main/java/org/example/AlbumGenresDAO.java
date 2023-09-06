package org.example;

import javax.xml.crypto.Data;
import java.util.*;
import java.sql.*;

public class AlbumGenresDAO {
    private static AlbumGenresDAO instance = null;

    public static AlbumGenresDAO getInstance() {
        if(instance == null)
            instance = new AlbumGenresDAO();
        return instance;
    }

    private AlbumGenresDAO() {};

    void addAlbumGenre(int artist_id, int genre_id) {
        try {
            Connection connection = Database.getConnection();
            CallableStatement st = (CallableStatement) connection.createStatement();
            int primaryKey = this.getMaximumPrimaryKey() + 1;
            String sqlStatement = String.format("INSERT INTO TABLE albumgenre VALUES(" +
                    Integer.toString(primaryKey) + ',' + Integer.toString(artist_id) + ',' + Integer.toString(genre_id) + ");");
            st.executeUpdate(sqlStatement);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getMaximumPrimaryKey() throws SQLException, ClassNotFoundException {

        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();

            String sqlStatament = "SELECT MAX(albumgenre_id) from albumgenre;";
            ResultSet resultSet = st.executeQuery(sqlStatament);
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<String> getGenreByAlbum(Album album) {

        List<String> genreList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatement = String.format("SELECT genre.name from albums as al \n" +
                    "INNER JOIN albumgenre as ag ON al.album_id = ag.album_id\n" +
                    "INNER JOIN genre ON ag.genre_id = genre.genre_id\n" +
                    "WHERE al.album_id = %2d;", album.getAlbum_id());
            ResultSet resultSet = st.executeQuery(sqlStatement);
            while (resultSet.next()) {
                genreList.add(resultSet.getString(1));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return genreList;
    }

    public List<String> getAlbumsByGenre(Genre genre) {
        List<String> albumList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatement = String.format("SELECT album.title from albums as al \n" +
                    "INNER JOIN albumgenre as ag ON al.album_id = ag.album_id\n" +
                    "INNER JOIN genre ON ag.genre_id = genre.genre_id\n" +
                    "WHERE genre.genre_id = %2d;", genre.getGenre_id());
            ResultSet resultSet = st.executeQuery(sqlStatement);
            while(resultSet.next()){
                albumList.add(resultSet.getString(1));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return albumList;
    }

}
