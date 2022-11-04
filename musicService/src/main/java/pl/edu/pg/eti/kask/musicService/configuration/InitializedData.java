package pl.edu.pg.eti.kask.musicService.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.musicService.element.entity.Song;
import pl.edu.pg.eti.kask.musicService.element.entity.Vocalist;
import pl.edu.pg.eti.kask.musicService.element.service.SongService;
import pl.edu.pg.eti.kask.musicService.element.service.VocalistService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitializedData {
    private final SongService songService;
    private final VocalistService vocalistService;

    @Autowired
    public InitializedData(SongService songService, VocalistService vocalistService) {
        this.songService = songService;
        this.vocalistService = vocalistService;
    }

    @PostConstruct
    private synchronized void init() {

        Vocalist rickAstley = Vocalist.builder()
                .name("Rick")
                .surname("Astley")
                .birthDate(LocalDate.of(1966, 2, 6))
                .albumsCount(3)
                .recordCompany("Step Records")
                .build();

        Vocalist taylorSwift = Vocalist.builder()
                .name("Taylor")
                .surname("Swift")
                .birthDate(LocalDate.of(1989, 12, 13))
                .albumsCount(9)
                .recordCompany("Atlantic Record")
                .build();

        Vocalist whitneyHouston = Vocalist.builder()
                .name("Whitney")
                .surname("Houston")
                .birthDate(LocalDate.of(1963, 8, 9))
                .albumsCount(6)
                .recordCompany("Arista Records")
                .build();

        Vocalist edSheeran = Vocalist.builder()
                .name("Edward")
                .surname("Sheeran")
                .birthDate(LocalDate.of(1991, 2, 17))
                .albumsCount(5)
                .recordCompany("Atlantic Records")
                .build();

        vocalistService.create(rickAstley);
        vocalistService.create(taylorSwift);
        vocalistService.create(whitneyHouston);
        vocalistService.create(edSheeran);

        Song s1 = Song.builder()
                .name("Never Gonna Give You Up")
                .vocalist(rickAstley)
                .genre("pop")
                .releaseYear(2009)
                .length(3.55)
                .build();

        Song s2 = Song.builder()
                .name("Bad Blood")
                .vocalist(taylorSwift)
                .genre("pop")
                .releaseYear(2015)
                .length(3.52)
                .build();

        Song s3 = Song.builder()
                .name("Look What You Made Me Do")
                .vocalist(taylorSwift)
                .genre("dance-pop")
                .releaseYear(2017)
                .length(3.52)
                .build();

        Song s4 = Song.builder()
                .name("You Belong With Me")
                .vocalist(taylorSwift)
                .genre("country-pop")
                .releaseYear(2008)
                .length(3.55)
                .build();

        Song s5 = Song.builder()
                .name("I Will Always Love You")
                .vocalist(whitneyHouston)
                .genre("country-pop")
                .releaseYear(1992)
                .length(4.52)
                .build();

        Song s6 = Song.builder()
                .name("I wanna Dance With Somebody")
                .vocalist(whitneyHouston)
                .genre("R&B/soul")
                .releaseYear(1987)
                .length(4.52)
                .build();

        Song s7 = Song.builder()
                .name("I Have Nothing")
                .vocalist(whitneyHouston)
                .genre("pop")
                .releaseYear(1992)
                .length(4.82)
                .build();

        Song s8 = Song.builder()
                .name("Photograph")
                .vocalist(edSheeran)
                .genre("R&B/soul")
                .releaseYear(2014)
                .length(4.3)
                .build();

        Song s9 = Song.builder()
                .name("Take Me Back To London")
                .vocalist(edSheeran)
                .genre("Hip-hop")
                .releaseYear(2019)
                .length(3.15)
                .build();

        Song s10 = Song.builder()
                .name("Galway Girl")
                .vocalist(edSheeran)
                .genre("pop")
                .releaseYear(2017)
                .length(2.83)
                .build();

        songService.create(s1);
        songService.create(s2);
        songService.create(s3);
        songService.create(s4);
        songService.create(s5);
        songService.create(s6);
        songService.create(s7);
        songService.create(s8);
        songService.create(s9);
        songService.create(s10);
    }

}
