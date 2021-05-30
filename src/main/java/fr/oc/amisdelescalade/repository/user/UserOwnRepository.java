package fr.oc.amisdelescalade.repository.user;

import fr.oc.amisdelescalade.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public interface UserOwnRepository {
    Optional<User> findByEmail(String email);

    @Repository
    @Transactional(readOnly = true)
    class DefaultUserOwnRepository implements UserOwnRepository{

        @PersistenceContext
        EntityManager entityManager;

        @Override
        public Optional<User> findByEmail(String email) {

            var query = entityManager.createNativeQuery("""
                SELECT id, email, password, password_confirm, full_name, user_name, country, creation_account, official_member 
                FROM USERS 
                WHERE email = ?
            """, User.class);
            query.setParameter(1, email); //Si bug essayé 0 x) TROP MARRANT

            return query.getResultList().stream().findFirst();
        }
    }
}

