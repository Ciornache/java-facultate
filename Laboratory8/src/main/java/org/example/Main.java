package org.example;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //TODO: After test implement function to extract data from excel

        ///Read Artists

        ArtistDAO artistDAO = ArtistDAO.getInstance();

        Scanner scanner = new Scanner(System.in);
        int artists = scanner.nextInt();
        scanner.nextLine();
        for(int i = 1;i <= artists; ++i) {
            String name = scanner.nextLine();
            int primaryKey = artistDAO.getMaximumPrimaryKey() + 1;
            Artist artist = new Artist(primaryKey, name);
            artistDAO.addArtist(artist);
        }

        ///Read Genres

        GenreDAO genreDAO = GenreDAO.getInstance();
        int genres = scanner.nextInt();
        scanner.nextLine();
        for(int i = 1;i <= genres; ++i) {
            String name = scanner.nextLine();
            /*
            System.out.println(name);
*/
            int primaryKey = genreDAO.getMaximumPrimaryKey() + 1;
            Genre genre = new Genre(primaryKey, name);
            genreDAO.addGenre(genre);
        }

        ///Read Albums + Genres

        AlbumDAO albumDAO = AlbumDAO.getInstance();
        int albums = scanner.nextInt();
        scanner.nextLine();

        AlbumGenresDAO albumGenresDAO = AlbumGenresDAO.getInstance();

        for(int i = 1;i <= albums; ++i) {
            String name = scanner.nextLine();
            String releaseYear = scanner.next();
            scanner.nextLine();
            int artist_id = scanner.nextInt();
            int primaryKey = albumDAO.getMaximumPrimaryKey() + 1;
            Album album = new Album(primaryKey, releaseYear, name, artist_id);
            albumDAO.addAlbum(album);

            int albumGenres = scanner.nextInt();
            scanner.nextLine();
            for(int j = 1;j <= albumGenres; ++j) {
                String genre = scanner.nextLine();
                try {
                    int genreId = genreDAO.findGenreByName(new StringBuilder(genre));
                    albumGenresDAO.addAlbumGenre(primaryKey, genreId);
                }
                catch(Exception e) {
                    e.printStackTrace();
                    j--;
                }
            }

        }

        List<String> genre = albumGenresDAO.getGenreByAlbum(albumDAO.getRandomAlbum());
        for(String gen : genre)
            System.out.println(gen);


        //DELETING ALL THE DATA
        albumGenresDAO.deleteAll();
        genreDAO.deleteAll();
        albumDAO.deleteAll();
        artistDAO.deleteAll();

        Database.getConnection().close();


        //        Connection connection = Database.getConnection();
//        Statement st = connection.createStatement();
//        ResultSet resultSet = st.executeQuery("Select * from artists");
//        while(resultSet.next()) {
//            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
//        }
    }
}