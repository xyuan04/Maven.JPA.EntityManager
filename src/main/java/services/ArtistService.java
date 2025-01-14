package services;

import entities.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArtistService {
    EntityManagerFactory emf;
    EntityManager em;

    public ArtistService() {
        emf = Persistence.createEntityManagerFactory("persist");
        em = emf.createEntityManager();
    }

    public Artist findById(Long id) {
        return em.find(Artist.class, id);
    }

    public List<Artist> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
        Root<Artist> rootEntry = cq.from(Artist.class);
        CriteriaQuery<Artist> all = cq.select(rootEntry);
        TypedQuery<Artist> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public boolean update(Long id, Artist artist) {
        Artist old = em.find(Artist.class, id);

        old.setFirstName(artist.getFirstName());
        old.setLastName(artist.getLastName());
        old.setInstrument(artist.getInstrument());

        em.getTransaction().begin();
        em.persist(old);
        em.getTransaction().commit();

        return true;
    }

    public boolean create(Artist artist) {
        em.getTransaction().begin();
        em.persist(artist);
        em.getTransaction().commit();
        return true;
    }

    public boolean delete(Long id) {
        Artist goner = findById(id);
        em.getTransaction().begin();
        em.remove(goner);
        em.getTransaction().commit();
        return true;
    }
}
