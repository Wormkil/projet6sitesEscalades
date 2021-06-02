package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.Bloc;
import fr.oc.amisdelescalade.model.ClimbSites;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface CSOwnRepository {
    List<ClimbSites> findAllBloc(Long id);

    @Repository
    @Transactional(readOnly = true)
    class DefaultCSOwnRepository implements CSOwnRepository {

        @PersistenceContext
        EntityManager entityManager;

        @Override
        public List<ClimbSites> findAllBloc(Long id){

            var query = entityManager.createNativeQuery("""
                SELECT id, number, quotes, notes
                FROM bloc 
                WHERE blocs_id = ?
            """, Bloc.class);
            query.setParameter(1, id); //Si bug essayé 0

            //return query.getResultList();
            return null;
        }

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

