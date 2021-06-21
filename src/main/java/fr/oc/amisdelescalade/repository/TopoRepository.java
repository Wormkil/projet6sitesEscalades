package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.Topo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopoRepository extends CrudRepository<Topo, Long>, TopoOwnRepository {

}
