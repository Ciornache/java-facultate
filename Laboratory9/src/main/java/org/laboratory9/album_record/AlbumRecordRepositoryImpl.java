package org.laboratory9.album_record;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.transaction.Transaction;
import org.laboratory9.EntityBuilder;
import org.laboratory9.album.Album;
import org.laboratory9.album.AlbumDAO;
import org.laboratory9.album.AlbumRepository;
import org.laboratory9.album.AlbumRepositoryImpl;
import org.laboratory9.genre.Genre;

import java.util.ArrayList;
import java.util.List;

public class AlbumRecordRepositoryImpl implements AlbumRecordRepository{
    @Override
    public void create(Album album, Genre genre) {

        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();

        t.begin();

        AlbumRecord albumRecord = new AlbumRecord(album, genre);
        if(em.contains(albumRecord))
            albumRecord = em.merge(albumRecord);

        em.persist(albumRecord);
        em.flush();

        t.commit();
        em.close();

    }

    @Override
    public AlbumRecord findById(int id) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("AlbumRecord.findById");
        query.setParameter("id", id);

        List<AlbumRecord> albumRecords = query.getResultList();

        if(albumRecords.size() == 0)
            return null;

        return albumRecords.get(0);
    }

    @Override
    public List<Album> findAlbumsByGenre(Genre genre) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("AlbumRecord.findAlbumsByGenre");
        query.setParameter("id", genre.getId());

        List<AlbumRecord> albumRecords = query.getResultList();

        List<Album> albums = new ArrayList<>();

        for(AlbumRecord record : albumRecords)
            albums.add(record.getAlbum());

        return albums;

    }

    @Override
    public List<Genre> findGenresByAlbums(Album album) {
        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("AlbumRecord.findAlbumsByGenre");
        query.setParameter("id", album.getId());

        List<AlbumRecord> albumRecords = query.getResultList();

        List<Genre> genres = new ArrayList<>();

        for(AlbumRecord record : albumRecords)
            genres.add(record.getGenre());

        return genres;
    }

    @Override
    public void reset() {

        EntityManagerFactory emf = EntityBuilder.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createNamedQuery("AlbumRecord.reset");
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();

    }
}
