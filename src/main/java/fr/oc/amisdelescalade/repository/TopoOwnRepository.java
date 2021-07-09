package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.Topo;
import fr.oc.amisdelescalade.model.User;
import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface TopoOwnRepository {
    List<Topo> findByAuthorId(String email);
    boolean existsByName(String name);
    List<Topo> findByAvailable(String available);

    @Repository
    @Transactional(readOnly = true)
    class DefaultTopoOwnRepository implements TopoOwnRepository{

        @PersistenceContext
        EntityManager entityManager;

        @Override
        public List<Topo> findByAuthorId(String authorId) {
            var query = entityManager.createNativeQuery("""
                SELECT id, name, description, localisation, releaseDate, available 
                FROM TOPOS 
                WHERE authorId = ?
            """, User.class);
            query.setParameter(1, authorId); //Si bug essayé 0 x) TROP MARRANT

            return query.getResultList();
        }

        @Override
        public boolean existsByName(String name) {
            var query = entityManager.createNativeQuery("""
                SELECT *
                FROM TOPOS 
                WHERE name = ?
            """, User.class);
            query.setParameter(1, name);
            try {
                query.getSingleResult();
            } catch (NoResultException e) {
                return false;
            } catch (NonUniqueResultException e) {
                return false;
            } finally {
                return true;
            }
        }
        @Override
        public List<Topo> findByAvailable(String available) {
            var query = entityManager.createNativeQuery("""
                SELECT *
                FROM TOPOS 
                WHERE available = ?
            """, User.class);
            query.setParameter(1, available);

            return query.getResultList();
        }
    }
}

