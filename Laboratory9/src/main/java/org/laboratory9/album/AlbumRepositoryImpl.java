package org.laboratory9.album;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.laboratory9.EntityBuilder;
import org.laboratory9.artist.Artist;

import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepository {
    @Override
    public void create(Album album) {
        AlbumDAO albumDAO = new AlbumDAOImpl();
        albumDAO.createAlbum(album);
        System.gc();
    }

    @Override
    public Album findById(int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Album.findById");
        List<Album> albumList = query.getResultList();
        em.close();
        if(albumList.size() == 0)
            return null;
        return albumList.get(0);
    }

    @Override
    public Album findByName(String name) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Album.findByTitle");
        List<Album> albumList = query.getResultList();
        em.close();
        if(albumList.size() == 0)
            return null;
        return albumList.get(0);
    }
}
