package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.Projet6Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface CSOwnRepository {
    //List<Bloc> findBlocsById(Long id);

    static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Repository
    @Transactional(readOnly = true)
    class DefaultCSOwnRepository implements CSOwnRepository {

        @PersistenceContext
        EntityManager entityManager;

        /*@Override
        public List<Bloc> findBlocsById(Long id){

            var query = entityManager.createNativeQuery("""
                SELECT number, quotes, notes
                FROM bloc 
                WHERE blocs_id = ?
            """, Bloc.class);
            query.setParameter(1, id); //Si bug essayé 0
            log.info("query : "+query.toString());
            return query.getResultList();

        }*/

      /*  @Override
        public Optional<User> findByEmail(String email) {

            var query = entityManager.createNativeQuery("""
                SELECT id, email, password, password_confirm, full_name, user_name, country, creation_account, official_member 
                FROM USERS 
                WHERE email = ?
            """, User.class);
            query.setParameter(1, email); //Si bug essayé 0

            return query.getResultList().stream().findFirst();
        }

        @Override
        public Optional<User> findByUserName(String userName) {

            var query = entityManager.createNativeQuery("""
                SELECT id, email, password, password_confirm, full_name, user_name, country, creation_account, official_member 
                FROM USERS 
                WHERE user_name = ?
            """, User.class);
            query.setParameter(1, userName); //Si bug essayé 0 x) TROP MARRANT

            return query.getResultList().stream().findFirst();
        }*/

    }
}

