package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.model.Bloc;
import fr.oc.amisdelescalade.model.Blocs;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.repository.BlocRepository;
import fr.oc.amisdelescalade.repository.BlocsRepository;
import fr.oc.amisdelescalade.repository.ClimbSitesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ClimbSitesService {

    @Autowired
    private ClimbSitesRepository climbSitesRepository;
    @Autowired
    private BlocsRepository blocsRepository;
    @Autowired
    private BlocRepository blocRepository;

    public Optional<ClimbSites> getCSById(final Long id) {
        return climbSitesRepository.findById(id);
    }
    public Iterable<ClimbSites> getCSs() {
        return climbSitesRepository.findAll();
    }
    public void deleteCS(final Long id) {
        climbSitesRepository.deleteById(id);
    }
    public ClimbSites saveCS(ClimbSites cs) {
        ClimbSites savedCS = climbSitesRepository.save(cs);
        return savedCS;
    }

    public Optional<Blocs> getBlocsById(final Long id) {
        return blocsRepository.findById(id);
    }
    public Iterable<Blocs> getBlocs() {
        return blocsRepository.findAll();
    }
    public void deleteBlocs(final Long id) {
        blocsRepository.deleteById(id);
    }
    public Blocs saveBlocs(Blocs bl) {
        Blocs savedBlocs = blocsRepository.save(bl);
        return savedBlocs;
    }

    public Optional<Bloc> getBlById(final Long id) {
        return blocRepository.findById(id);
    }
    public Iterable<Bloc> getBl() {
        return blocRepository.findAll();
    }
    public void deleteBl(final Long id) {
        blocRepository.deleteById(id);
    }
    public Bloc saveBl(Bloc bl) {
        Bloc savedBl = blocRepository.save(bl);
        return savedBl;
    }

}
