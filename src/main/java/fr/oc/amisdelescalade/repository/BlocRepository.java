package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.Bloc;
import org.springframework.data.repository.CrudRepository;

public interface BlocRepository extends CrudRepository<Bloc, Long>, BlocOwnRepository {
}
