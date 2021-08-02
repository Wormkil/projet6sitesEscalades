package fr.oc.amisdelescalade.repository;

public interface CsOwnRepository {
  /*  List<ClimbSites> findCssByCs(ClimbSites cs);

    @Repository
    @Transactional(readOnly = true)
    class DefaultCsOwnRepository implements CsOwnRepository {

        @PersistenceContext
        EntityManager entityManager;

        @Override
        public List<ClimbSites> findCssByCs(ClimbSites cs) {
            var query = entityManager.createNativeQuery("""
                SELECT *
                FROM climbsites 
                WHERE blocs_id = ?
            """, ClimbSites.class);
            query.setParameter(1, id); //Si bug essayé 0

            return query.getResultList();
        }
    }*/
}