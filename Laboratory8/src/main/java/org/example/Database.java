package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Database {

    private static Database database = null;

    private static Connection connection = null;

    private Database() {

    }

    public static synchronized Database getInstance() {
        if(database == null)
            database = new Database();
        return database;
    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
         return HikariDatabasePoolConnection.getConnection();
    }

    public static void addDataToDatabase(int releaseDateYear, String albumName, String artistname, List<String> genres) throws SQLException, ClassNotFoundException, InvalidGenreException {

        System.out.println(albumName);

        /// ADD Artist
        ArtistDAO artistDAO = ArtistDAO.getInstance();
        int artistPrimaryKey = artistDAO.getMaximumPrimaryKey() + 1;
        Artist artist = new Artist(artistPrimaryKey, artistname);
        artistDAO.addArtist(artist);

        ///ADD Genre

        GenreDAO genreDAO = GenreDAO.getInstance();
        for (String s : genres) {
            if(s.length() < 50) {
                int genrePrimaryKey = genreDAO.getMaximumPrimaryKey() + 1;
                Genre genre = new Genre(genrePrimaryKey, s);
                genreDAO.addGenre(genre);
            }
        }

        ///ADD Album

        AlbumDAO albumDAO = AlbumDAO.getInstance();
        int primaryKey = albumDAO.getMaximumPrimaryKey() + 1;

        for(int j = 0;j < albumName.length(); ++j) {
            if((int)(albumName.charAt(j)) == 39)
                return;
        }

        Album album = new Album(primaryKey, Integer.toString(releaseDateYear), albumName, artistPrimaryKey);
        try {
            albumDAO.addAlbum(album);
        }
        catch(Exception e) {
            System.out.println("Invalid Name");
            return;
        }
        ///ADD Album and Genres

        AlbumGenresDAO albumGenresDAO = AlbumGenresDAO.getInstance();
        for(String gen : genres){
            if(gen.length() < 50) {
                int genreKey = genreDAO.findGenreByName(new StringBuilder(gen));
                albumGenresDAO.addAlbumGenre(primaryKey, genreKey);
            }
        }


    }
}
