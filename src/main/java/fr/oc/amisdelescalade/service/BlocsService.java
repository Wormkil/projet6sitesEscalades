package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.model.Blocs;
import fr.oc.amisdelescalade.repository.BlocsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class BlocsService {

    @Autowired
    private BlocsRepository blocsRepository;

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
}
