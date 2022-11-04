package pl.edu.pg.eti.kask.musicService.element.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.kask.musicService.element.entity.Song;
import pl.edu.pg.eti.kask.musicService.element.repository.SongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private SongRepository repository;

    @Autowired
    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public Optional<Song> find(String name) {
        return repository.find(name);
    }

    public List<Song> findAll() {
        return repository.findAll();
    }

    public void create(Song song) {
        repository.create(song);
    }

    public void delete(Song song) {
        repository.delete(song);
    }

    public void update(Song song) {
        repository.update(song);
    }
}
