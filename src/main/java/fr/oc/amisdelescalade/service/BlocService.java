package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.model.Bloc;
import fr.oc.amisdelescalade.repository.BlocRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class BlocService {

    @Autowired
    private BlocRepository blocRepository;

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
    public List<Bloc> findAllBlocs(Long id){

        return blocRepository.findBlocByBlocsId(id);
    }
}
