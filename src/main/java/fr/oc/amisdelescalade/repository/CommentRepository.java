package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long>, CommentOwnRepository {
}
