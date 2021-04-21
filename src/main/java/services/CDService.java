package services;

import entities.CD;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CDService {
    EntityManagerFactory emf;
    EntityManager em;

    public CDService() {
        emf = Persistence.createEntityManagerFactory("persist");
        em = emf.createEntityManager();
    }

    public CD findById(Long id) {
        return em.find(CD.class, id);
    }

    public List<CD> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CD> cq = cb.createQuery(CD.class);
        Root<CD> rootEntry = cq.from(CD.class);
        CriteriaQuery<CD> all = cq.select(rootEntry);
        TypedQuery<CD> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public boolean update(Long id, CD cd) {
        CD og = findById(id);

        og.setTitle(cd.getTitle());
        og.setDesc(cd.getDesc());
        og.setYear(cd.getYear());
        og.setArtists(cd.getArtists());
        og.setPrice(cd.getPrice());

        em.getTransaction().begin();
        em.persist(og);
        em.getTransaction().commit();

        return true;
    }

    public boolean create(CD cd) {
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();

        return true;
    }

    public boolean delete(Long id) {
        CD goner = findById(id);

        em.getTransaction().begin();
        em.remove(goner);
        em.getTransaction().commit();

        return true;
    }
}
