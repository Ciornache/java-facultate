package org.laboratory9.artist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.laboratory9.EntityBuilder;
import org.laboratory9.album.Album;

import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepository{

    ArtistDAO artistDAO = new ArtistDAOImpl();

    @Override
    public void create(Artist artist) {
        artistDAO.createArtist(artist);
    }

    @Override
    public Artist findArtistById(int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Artist.findById");
        query.setParameter("id", id);
        List<Artist> resultList = query.getResultList();
        em.close();
        if(resultList.size() == 0)
            return null;
        return resultList.get(0);
    }

    @Override
    public Artist findArtistByName(String name) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Artist.findByName");
        query.setParameter("name", name);
        List<Artist> resultList = query.getResultList();
        em.close();
        if(resultList.size() == 0)
            return null;
        return resultList.get(0);
    }

    @Override
    public List<Album> getArtistAlbums(int id)
    {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createNamedQuery("Artist.getAlbums");
        query.setParameter("id", id);
        List<Album> albumList = query.getResultList();

        em.close();
        try {
            return albumList;
        }
        catch (NullPointerException e) {
            e.getStackTrace();
            throw new NullPointerException("Didn't find any albums for artist " + id);
        }
    }
}
