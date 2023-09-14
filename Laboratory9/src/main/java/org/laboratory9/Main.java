package org.laboratory9;

import jakarta.persistence.EntityManager;
import org.laboratory9.album.Album;
import org.laboratory9.album.AlbumDAOImpl;
import org.laboratory9.album.AlbumRepository;
import org.laboratory9.album.AlbumRepositoryImpl;
import org.laboratory9.artist.*;
import org.laboratory9.genre.Genre;
import org.laboratory9.genre.GenreDAO;
import org.laboratory9.genre.GenreDAOImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AlbumDAOImpl albumDAO = new AlbumDAOImpl();
            ArtistDAOImpl artistDAO = new ArtistDAOImpl();

            // Create four artists
            Artist artist1 = new Artist("Artist 1 Name", "1990-01-01");
            Artist artist2 = new Artist("Artist 2 Name", "1985-03-15");
            Artist artist3 = new Artist("Artist 3 Name", "1995-07-20");
            Artist artist4 = new Artist("Artist 4 Name", "1980-12-05");

            // Persist the artists

            // Create and associate albums with artists
            Album album1 = new Album("Album 1 Title", "2021", artist1);
            Album album2 = new Album("Album 2 Title", "2022", artist2);
            Album album3 = new Album("Album 3 Title", "2023", artist3);
            Album album4 = new Album("Album 4 Title", "2024", artist4);

//            artist1.getAlbumList().add(album1);
//            artist2.getAlbumList().add(album2);
//            artist3.getAlbumList().add(album3);
//            artist4.getAlbumList().add(album4);

            artistDAO.createArtist(artist1);
            artistDAO.createArtist(artist2);
            artistDAO.createArtist(artist3);
            artistDAO.createArtist(artist4);

            System.out.println(artist1.getId());

            albumDAO.createAlbum(album1);
            albumDAO.createAlbum(album2);
            albumDAO.createAlbum(album3);
            albumDAO.createAlbum(album4);

//            Object id = EntityBuilder.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(artist1);
//            System.out.println(id);
//
//            ArtistRepository artistRepository = new ArtistRepositoryImpl();
//
//            System.out.println();
//
//            List<Album> albumList = artistRepository.getArtistAlbums(artist1.getId());
//            System.out.println(albumList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
