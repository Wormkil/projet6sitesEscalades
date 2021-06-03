package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Bloc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface BlocOwnRepository {
    static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    List<Bloc> findBlocByBlocsId(Long id);

    @Repository
    @Transactional(readOnly = true)
    class DefaultBlocOwnRepository implements BlocOwnRepository {

        @PersistenceContext
        EntityManager entityManager;

        @Override
        public List<Bloc> findBlocByBlocsId(Long id) {
            log.info("je suis ici");
            var query = entityManager.createNativeQuery("""
                SELECT number, quotes, notes
                FROM bloc 
                WHERE blocs_id = ?
            """, Bloc.class);
            query.setParameter(1, id); //Si bug essayé 0
            log.info("je suis ici");
            return query.getResultList();

        }
    }
}
