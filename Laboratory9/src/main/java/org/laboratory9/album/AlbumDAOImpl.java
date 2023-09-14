package org.laboratory9.album;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.laboratory9.EntityBuilder;
import  java.util.*;

public class AlbumDAOImpl implements AlbumDAO{


    @Override
    public void createAlbum(Album album) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(em.contains(album))
            album = em.merge(album);
        em.persist(album);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateAlbum(Album album, int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album oldAlbum = em.find(Album.class, id);
        oldAlbum.replace(album);
        em.close();
    }

    @Override
    public void deleteAlbum(int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album oldAlbum = em.find(Album.class, id);
        em.remove(oldAlbum);
        em.close();
    }

    @Override
    public void printAllAlbums() {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Album.selectAll");
        List<Album> albums = query.getResultList();
        for(Album album : albums) {
            System.out.println("Album Name: " + album.getName());
            System.out.println("Album release year" + album.getReleaseYear());
            System.out.println("Album composed by artist " + album.getArtist());
            System.out.println("Album identified by id " + album.getId());
            System.out.println();
        }
        em.close();
    }

    @Override
    public void reset() {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Query query = em.createNamedQuery("Album.reset");
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Album> getAllAlbums() {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Album.selectAll");
        List<Album> albums = query.getResultList();
        em.close();
        return albums;
    }
}
