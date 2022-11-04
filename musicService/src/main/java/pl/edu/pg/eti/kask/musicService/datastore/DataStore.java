package pl.edu.pg.eti.kask.musicService.datastore;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.musicService.element.entity.Song;
import pl.edu.pg.eti.kask.musicService.element.entity.Vocalist;
import pl.edu.pg.eti.kask.musicService.serialization.ClonningUtility;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Log
@Component
public class DataStore {

    private Set<Song> songs = new HashSet<>();
    private Set<Vocalist> vocalists = new HashSet<>();

    public synchronized List<Song> findAllSongs() {
        return songs.stream().map(ClonningUtility::clone).collect(Collectors.toList());
    }

    public Optional<Song> findSong(String name) {
        return songs.stream()
                .filter(song -> song.getName().equals(name))
                .findFirst()
                .map(ClonningUtility::clone);

    }

    public synchronized void createSong(Song song) throws IllegalArgumentException {
        findSong(song.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The song name \"%s\" is not unique", song.getName()));
                },
                () -> songs.add(song));
    }

    public synchronized void deleteSong(String name) throws IllegalArgumentException {
        findSong(name).ifPresentOrElse(
                original -> songs.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The song with name \"%s\" does not exist", name));
                });
    }

    public synchronized void updateSong(Song song) throws IllegalArgumentException {
        findSong(song.getName()).ifPresentOrElse(
                original -> {
                    songs.remove(original);
                    songs.add(song);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The song \"%s\" does not exist", song.getName()));
                });
    }
    public synchronized List<Vocalist> findAllVocalists() {
        return vocalists.stream().map(ClonningUtility::clone).collect(Collectors.toList());
    }

    public Optional<Vocalist> findVocalist(String name, String surname) {
        return vocalists.stream()
                .filter(vocalist -> vocalist.getName().equals(name))
                .filter(vocalist -> vocalist.getSurname().equals(surname))
                .findFirst()
                .map(ClonningUtility::clone);
    }

    public synchronized void createVocalist(Vocalist vocalist) throws IllegalArgumentException {
        findVocalist(vocalist.getName(), vocalist.getSurname()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The vocalist called \"%s %s\" is not unique", vocalist.getName(), vocalist.getSurname()));
                },
                () -> vocalists.add(vocalist));
    }

    public synchronized void deleteVocalist(String name, String surname) throws IllegalArgumentException {
        findVocalist(name, surname).ifPresentOrElse(
                original -> vocalists.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The vocalist called \"%s %s\" does not exist", name, surname));
                });
    }
}
