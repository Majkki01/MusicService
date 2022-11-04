package pl.edu.pg.eti.kask.musicService.element.service;

import org.springframework.stereotype.Service;
import pl.edu.pg.eti.kask.musicService.element.entity.Vocalist;
import pl.edu.pg.eti.kask.musicService.element.repository.VocalistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VocalistService {

    private VocalistRepository repository;

    public VocalistService(VocalistRepository repository) {
        this.repository = repository;
    }

    public Optional<Vocalist> find(String[] id) {
        return repository.find(id);
    }

    public List<Vocalist> findAll() {
        return repository.findAll();
    }

    public void create(Vocalist vocalist) {
        repository.create(vocalist);
    }

    public void delete(Vocalist vocalist) {
        repository.delete(vocalist);
    }
}
