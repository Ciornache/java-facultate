package org.laboratory9.genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.laboratory9.EntityBuilder;
import org.laboratory9.album.Album;

import java.util.List;

public class GenreRepositoryImpl implements GenreRepository{

    GenreDAO GenreDAO = new GenreDAOImpl();

    @Override
    public void create(Genre genre) {
        GenreDAO.createGenre(genre);
    }

    @Override
    public Genre findGenreById(int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Genre.findById");
        query.setParameter("id", id);
        List<Genre> resultList = query.getResultList();
        if(resultList.size() == 0)
            return null;
        return resultList.get(0);
    }

    @Override
    public Genre findGenreByName(String name) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Genre.findByName");
        query.setParameter("name", name);
        List<Genre> resultList = query.getResultList();
        if(resultList.size() == 0)
            return null;
        return resultList.get(0);
    }

    @Override
    public List<Album> getAlbums(Genre Genre) {
        return null;
    }
}
