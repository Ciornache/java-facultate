package org.laboratory9;

import jakarta.persistence.EntityManager;
import org.laboratory9.album.*;
import org.laboratory9.album_record.AlbumRecord;
import org.laboratory9.album_record.AlbumRecordRepository;
import org.laboratory9.album_record.AlbumRecordRepositoryImpl;
import org.laboratory9.artist.*;
import org.laboratory9.genre.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        /// Initialize DAO classes

        ArtistDAO artistDAO = new ArtistDAOImpl();
        AlbumDAO albumDAO = new AlbumDAOImpl();
        GenreDAO genreDAO = new GenreDAOImpl();

        /// Initialize repositories
        AlbumRecordRepository arr = new AlbumRecordRepositoryImpl();

        /// Reseting the database

        arr.reset();
        albumDAO.reset();
        artistDAO.reset();
        genreDAO.reset();

        /// Populating the database and testing

        String fileName = "TestingUnits/Test2.txt";

        Scanner scanner = new Scanner(new File(fileName));
        int numberOfArtists = scanner.nextInt();
        scanner.nextLine();
        for(int inst = 1; inst <= numberOfArtists; ++inst) {

            String name = scanner.nextLine();
            String birthDate = scanner.nextLine();

            Artist artist = new Artist(name, birthDate);
            artistDAO.createArtist(artist);
        }

        int numberOfGenres = scanner.nextInt();
        System.out.println(numberOfGenres);
        scanner.nextLine();
        for(int inst = 1;inst <= numberOfGenres; ++inst) {
            String name = scanner.nextLine();
            Genre genre = new Genre(name);
            try {
                genreDAO.createGenre(genre);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Artist> artistList = artistDAO.getAllArtist();

        int numberOfAlbums = scanner.nextInt();
        scanner.nextLine();

        for(int inst = 1; inst <= numberOfAlbums; ++inst) {

            String name = scanner.nextLine();
            String releaseYear = scanner.nextLine();

            Random random = new Random();
            Artist artist = artistList.get(Math.abs(random.nextInt()) % artistList.size());

            Album album = new Album(name, releaseYear, artist);
            albumDAO.createAlbum(album);
        }

        List<Genre> genres = genreDAO.getAllGenres();
        List<Album> albums = albumDAO.getAllAlbums();

        int records = 50;

        for(int inst = 1; inst <= records; ++inst) {
            Random random = new Random();
            Genre genre = genres.get(Math.abs(random.nextInt()) % genres.size());
            Album album = albums.get(Math.abs(random.nextInt()) % albums.size());
            arr.create(album, genre);
        }


        EntityBuilder.getEntityManagerFactory().close();

    }

}
