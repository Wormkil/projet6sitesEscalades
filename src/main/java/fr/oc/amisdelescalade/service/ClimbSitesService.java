package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.repository.CSRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ClimbSitesService {

    @Autowired
    private CSRepository cSRepository;

    public Optional<ClimbSites> getCSById(final Long id) {
        return cSRepository.findById(id);
    }
    public Iterable<ClimbSites> getCSs() {
        return cSRepository.findAll();
    }
    public void deleteCS(final Long id) {
        cSRepository.deleteById(id);
    }
    public ClimbSites saveCS(ClimbSites cs) {
        ClimbSites savedCS = cSRepository.save(cs);
        return savedCS;
    }

}
