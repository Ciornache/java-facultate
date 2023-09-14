package org.laboratory9.genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Query;
import org.laboratory9.EntityBuilder;

import java.util.List;


public class GenreDAOImpl implements GenreDAO {

    @Override
    public void createGenre(Genre genre) {

        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = (EntityManager) emf.createEntityManager();
        em.getTransaction().begin();

        if(!em.contains(genre))
            genre = em.merge(genre);

        em.persist(genre);
        em.getTransaction().commit();

        em.close();

    }

    @Override
    public void updateGenre(Genre genre, int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Genre oldGenre = em.find(Genre.class, id);
        oldGenre.replace(genre.getName());

        em.getTransaction().commit();

        System.out.println(oldGenre);

        em.close();

    }

    @Override
    public void deleteGenre(int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Genre genre = em.find(Genre.class, id);
        em.remove(genre);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void printAllGenres() {

        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("genre.selectAll");
        List<Genre> list = query.getResultList();
        for(Genre genre : list) {
            System.out.println("Name is " + genre.getName());
            System.out.println("Identified by id " + genre.getId());
            System.out.println();
        }

        em.close();
    }

    public GenreDAOImpl() {};

}
