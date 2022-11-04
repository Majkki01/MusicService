package pl.edu.pg.eti.kask.musicService.repository;

import pl.edu.pg.eti.kask.musicService.element.entity.Song;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
    Optional<E> find(K id);

    List<E> findAll();
    void create(E entity);
    void delete(E entity);
    void update(E entity);
}
