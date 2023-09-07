package org.example;

import com.sun.jdi.request.StepRequest;

import javax.xml.crypto.Data;
import java.net.CookieHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class ArtistDAO {

    private final List<Artist> artistList = new ArrayList<>();
    Database database = Database.getInstance();
    private static ArtistDAO instance = null;

    public void updateArtist(int id, Artist artist) throws SQLException, ClassNotFoundException {
        Artist artist2 = findArtistById(id);
        artistList.remove(artist2);
        artist.setId(artist2.getId());
        removeArtist(artist2);
        addArtist(artist);
        artistList.add(artist);
    }

    public void removeArtist(Artist artist) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatament = String.format("DELETE FROM artist where artist.id = %2d;", artist.getId());
            st.executeUpdate(sqlStatament);
            artistList.remove(artist);
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addArtist(Artist artist) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlInsert = "INSERT INTO artist VALUES(" + artist.insertForm() + ");";
//            System.out.println(sqlInsert);
            st.executeUpdate(sqlInsert);
            artistList.add(artist);
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void printAllArtists() {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();

            String sqlStatament = "SELECT * from artist;";
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

    private ArtistDAO() {}

    public static ArtistDAO getInstance() {
        if(instance == null)
            instance = new ArtistDAO();
        return instance;
    }

    public int getMaximumPrimaryKey() throws SQLException, ClassNotFoundException {

        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatament = "SELECT MAX(artist_id) from artist;";
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

    public Artist findArtistById(int id) {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatament = String.format("SELECT * from artist WHERE artist.id = %2d;", id);
            ResultSet resultSet = st.executeQuery(sqlStatament);
            resultSet.next();
            Artist artist = new Artist(resultSet.getInt(1), resultSet.getString(2));
            connection.close();
            return artist;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Artist findArtistByName(String name) {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            String sqlStatament = String.format("SELECT * from artist WHERE artist.name = %s;", name);
            ResultSet resultSet = st.executeQuery(sqlStatament);
            resultSet.next();
            connection.close();
            return new Artist(resultSet.getInt(1), resultSet.getString(2));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteAll() {
        try {
            Connection connection = Database.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("Delete from artist;");
            connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
