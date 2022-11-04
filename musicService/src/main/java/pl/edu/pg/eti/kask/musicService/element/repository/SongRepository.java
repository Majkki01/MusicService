package pl.edu.pg.eti.kask.musicService.element.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.kask.musicService.datastore.DataStore;
import pl.edu.pg.eti.kask.musicService.element.entity.Song;
import pl.edu.pg.eti.kask.musicService.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class SongRepository implements Repository<Song, String> {

    private DataStore store;

    @Autowired
    public SongRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Song> find(String id) {
        return store.findSong(id);
    }

    @Override
    public List<Song> findAll() {
        return store.findAllSongs();
    }

    @Override
    public void create(Song entity) {
        store.createSong(entity);
    }

    @Override
    public void delete(Song entity) {
        store.deleteSong(entity.getName());
    }

    @Override
    public void update(Song entity) {
        store.updateSong(entity);
    }
}
