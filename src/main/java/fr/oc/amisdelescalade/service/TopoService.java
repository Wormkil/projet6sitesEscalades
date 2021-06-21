package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.model.Topo;
import fr.oc.amisdelescalade.repository.TopoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class TopoService {
    @Autowired
    private TopoRepository topoRepository;

    public Optional<Topo> getTopoById(final Long id) {
        return topoRepository.findById(id);
    }
    public Iterable<Topo> getTopos() {
        return topoRepository.findAll();
    }
    public void deleteTopo(final Long id) {
        topoRepository.deleteById(id);
    }
    public Topo saveTopo(Topo tp) {
        Topo savedTopo = topoRepository.save(tp);
        return savedTopo;
    }

    public List<Topo> getToposByAuthorId(String authorId){
        return topoRepository.findByAuthorId(authorId);
    }
}
