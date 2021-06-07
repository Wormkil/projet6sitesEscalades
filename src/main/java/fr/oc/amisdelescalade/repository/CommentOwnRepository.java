package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface CommentOwnRepository {
    List<Comment> findCommentByCsId(Long id);

    @Repository
    @Transactional(readOnly = true)
    class DefaultCommentOwnRepository implements CommentOwnRepository {
        @PersistenceContext
        EntityManager entityManager;

        @Override
        public List<Comment> findCommentByCsId(Long id) {
            var query = entityManager.createNativeQuery("""
                SELECT userId, content, creationDate
                FROM comments 
                WHERE csId = ?
            """, Comment.class);
            query.setParameter(1, id); //Si bug essayé 0

            return query.getResultList();

        }
    }
}
