package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, UserOwnRepository {

}
