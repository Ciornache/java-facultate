package org.laboratory9.artist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Query;
import org.laboratory9.EntityBuilder;

import java.util.List;


public class ArtistDAOImpl implements ArtistDAO {

    @Override
    public void createArtist(Artist artist) {

//        System.out.println(artist);

        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = (EntityManager) emf.createEntityManager();
        em.getTransaction().begin();

        if(!em.contains(artist))
            artist = em.merge(artist);

        em.persist(artist);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void updateArtist(Artist artist, int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Artist oldArtist = em.find(Artist.class, id);
        oldArtist.replace(artist.getName(), artist.getBirthDate());

        em.getTransaction().commit();

        System.out.println(oldArtist);

        em.close();

    }

    @Override
    public void deleteArtist(int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Artist artist = em.find(Artist.class, id);
        em.remove(artist);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void printAllArtists() {

        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("Artist.selectAll");
        List<Artist> list = query.getResultList();
        for(Artist artist : list) {
            System.out.println("Name is " + artist.getName());
            System.out.println("Birth Date is " + artist.getBirthDate());
            System.out.println("Identified by id " + artist.getId());
            System.out.println();
        }

        em.close();
    }

    public ArtistDAOImpl() {};


}
