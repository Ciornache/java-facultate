package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    private final List<Album> albumList = new ArrayList<>();

    private Connection connection = null;
    private static AlbumDAO instance = null;

    private AlbumDAO() throws SQLException, ClassNotFoundException {
//        initiateAlbumList();
    }

    public static AlbumDAO getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null)
            instance = new AlbumDAO();
        return instance;
    }

    public void addAlbum(Album album) throws SQLException, ClassNotFoundException {
            connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatement = "INSERT INTO ALBUM VALUES(" + album.insertForm() + ");";
//            System.out.println(sqlStatement);
            st.executeUpdate(sqlStatement);
            albumList.add(album);
            connection.close();
    }

    public void deleteAlbum(Album album) {
        try {
            connection = Database.getConnection();
            Statement st = connection.createStatement();
            int album_id = album.getAlbum_id();
            String sqlStatament = String.format("DELETE FROM album WHERE album.album_id = %2d;", album_id);
            st.executeUpdate(sqlStatament);
            albumList.remove(album);
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAlbum(int album_id, Album album) throws SQLException, ClassNotFoundException {
        Album album2 = findAlbumById(album_id);
        assert album2 != null;
        album.setAlbum_id(album2.getAlbum_id());
        deleteAlbum(album2);
        albumList.remove(album2);
        addAlbum(album);
        albumList.add(album);
    }

    private Album findAlbumById(int album_id) {
        try {
            connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatement = String.format("SELECT * from album WHERE album.album_id = %2d;", album_id);
            ResultSet resultSet = st.executeQuery(sqlStatement);
            resultSet.next();
            Album album = new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            connection.close();
            return album;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initiateAlbumList() {
        try {
            connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatement = "SELECT * from album";
            ResultSet resultSet = st.executeQuery(sqlStatement);
            while(resultSet.next()) {
                albumList.add(new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
            }
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void printAllAlbums() {
        for(Album alb : albumList)
            System.out.println(alb);
    }

    public int getMaximumPrimaryKey() throws SQLException, ClassNotFoundException {

        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();

            String sqlStatament = "SELECT MAX(album_id) from album;";
            ResultSet resultSet = st.executeQuery(sqlStatament);
            resultSet.next();
            int val = resultSet.getInt(1);
            connection.close();
            return val;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Album getRandomAlbum() {
        double rand = Math.random() * (double) albumList.size();
        if((int)(rand) == 0)
            return albumList.get(0);
        else
            return albumList.get((int)(rand) - 1);
    }

    public void deleteAll() {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("Delete from album;");
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    ///CRUD

    /// CREATE
    /// RETRIEVE
    /// UPDATE
    /// DELETE
}
