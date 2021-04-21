package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtistService {
    EntityManagerFactory emf;
    EntityManager em;

    public ArtistService() {
        emf = Persistence.createEntityManagerFactory("persist");
        em = emf.createEntityManager();
    }


}
