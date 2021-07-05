package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Topo;
import fr.oc.amisdelescalade.repository.TopoRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class TopoService {
    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
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
    public boolean existsByName(String name){ return topoRepository.existsByName(name);}

}
