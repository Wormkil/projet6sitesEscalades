package fr.oc.amisdelescalade.repository;

import fr.oc.amisdelescalade.model.ClimbSites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CSRepository extends CrudRepository<ClimbSites, Long>, CSOwnRepository {
    /*@Override
    default List<ClimbSites> findAllBloc(Long id){
        return null;
    }*/
}
