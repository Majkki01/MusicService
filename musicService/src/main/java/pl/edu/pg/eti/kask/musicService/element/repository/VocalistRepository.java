package pl.edu.pg.eti.kask.musicService.element.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.kask.musicService.datastore.DataStore;
import pl.edu.pg.eti.kask.musicService.element.entity.Song;
import pl.edu.pg.eti.kask.musicService.element.entity.Vocalist;
import pl.edu.pg.eti.kask.musicService.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class VocalistRepository implements Repository<Vocalist, String[]> {

    private DataStore store;

    @Autowired
    public VocalistRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Vocalist> find(String[] id) {
        return store.findVocalist(id[0], id[1]);
    }

    @Override
    public List<Vocalist> findAll() {
        return store.findAllVocalists();
    }

    @Override
    public void create(Vocalist entity) {
        store.createVocalist(entity);
    }

    @Override
    public void delete(Vocalist entity) {
        store.deleteVocalist(entity.getName(), entity.getSurname());
    }

    @Override
    public void update(Vocalist entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }
}
