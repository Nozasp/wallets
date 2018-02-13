package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.jpa.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaConnector {

    EntityManagerFactory factory;

    void connect(){
        if(this.factory == null){
            this.factory = Persistence.createEntityManagerFactory("cryptos");
        }

    }
    public EntityManager createEntityManager() {
        // if already connected, do noting
        this.connect();
        return factory.createEntityManager();
    }

    public void close (){
        this.factory.close();

    }
    public static void main(String[] args) {
        JpaConnector connector = new JpaConnector();
        EntityManager em = connector.createEntityManager();

        JpaUser jean = new JpaUser();
        jean.setName("Jean");

        JpaUser jack = new JpaUser();
        jack.setName("Jack");

        JpaUser jackies = new JpaUser();
        jackies.setName("Jackies");

        JpaUser jules = new JpaUser();
        jules.setName("Jules");

        JpaUser jasper = new JpaUser();
        jasper.setName("Jasper");

        //OPen the box pour mettre ces gens dans la base de donnée
        em.getTransaction().begin();

        em.persist(jean);
        em.persist(jack);
        em.persist(jackies);
        em.persist(jules);
        em.persist(jasper);
        em.getTransaction().commit();
        em.close();
        connector.close();
    }


}
