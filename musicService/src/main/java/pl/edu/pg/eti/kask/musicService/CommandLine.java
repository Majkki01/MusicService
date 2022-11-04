package pl.edu.pg.eti.kask.musicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.musicService.element.entity.Song;
import pl.edu.pg.eti.kask.musicService.element.entity.Vocalist;
import pl.edu.pg.eti.kask.musicService.element.service.SongService;
import pl.edu.pg.eti.kask.musicService.element.service.VocalistService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner{

    private SongService songService;
    private VocalistService vocalistService;

    @Autowired
    public CommandLine(SongService songService, VocalistService vocalistService) {
        this.songService = songService;
        this.vocalistService = vocalistService;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean running = true;

        while(running) {

            System.out.println("WELCOME IN MUSIC SERVICE");
            System.out.println("------------------------");
            System.out.println("MENU (Choose your action)");
            System.out.println("1. List all the songs (with vocalists)");
            System.out.println("2. List all the vocalists");
            System.out.println("3. Add the song");
            System.out.println("4. Add the vocalist");
            System.out.println("5. Delete the song");
            System.out.println("6. Delete the vocalist");
            System.out.println("7. Exit");
            System.out.println("ACTION No.: ");

            Scanner scanner = new Scanner(System.in);

            Scanner scan1 = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            Scanner scan3 = new Scanner(System.in);
            Scanner scan4 = new Scanner(System.in);
            Scanner scan5 = new Scanner(System.in);
            int action = scanner.nextInt();

            switch (action) {
                case 1 -> songService.findAll().forEach(System.out::println);
                case 2 -> vocalistService.findAll().forEach(System.out::println);
                case 3 -> {
                    System.out.println("Enter new song name");
                    String songName = scan1.nextLine();

                    System.out.println("Choose the vocalist (number):");
                    List<Vocalist> vocalists = vocalistService.findAll();
                    for(int i = 0; i < vocalists.size(); i++) {
                        System.out.println(i+1 + ". " + vocalists.get(i).getName() + " " + vocalists.get(i).getSurname());
                    }
                    System.out.println("CHOICE: ");
                    Vocalist vocalist = vocalists.get(scan2.nextInt() - 1);

                    System.out.println("Enter new song genre");
                    String genre = scan3.nextLine();

                    System.out.println("Enter new song release year");
                    int releaseYear = scan4.nextInt();

                    System.out.println("Enter new song time length");
                    double length = scan5.nextDouble();

                    Song s = Song.builder()
                            .name(songName)
                            .vocalist(vocalist)
                            .genre(genre)
                            .releaseYear(releaseYear)
                            .length(length)
                            .build();

                    songService.create(s);
                }
                case 4 -> {
                    System.out.println("Enter new vocalist name");
                    String name = scan1.nextLine();

                    System.out.println("Enter new vocalist surname");
                    String surname = scan2.nextLine();

                    System.out.println("Enter new vocalist date of birth (YYYY-MM-DD)");
                    String birthDateStr = scan3.nextLine();
                    LocalDate birthDate = LocalDate.parse(birthDateStr);

                    System.out.println("Enter new vocalist number of albums");
                    int albumsCount = scan4.nextInt();

                    System.out.println("Enter new vocalist record company");
                    String recordCompany = scan5.nextLine();

                    Vocalist v = Vocalist.builder()
                            .name(name)
                            .surname(surname)
                            .birthDate(birthDate)
                            .albumsCount(albumsCount)
                            .recordCompany(recordCompany)
                            .build();

                    vocalistService.create(v);
                }
                case 5 -> {
                    System.out.println("Choose the song to delete (number):");
                    List<Song> songs = songService.findAll();
                    for(int i = 0; i < songs.size(); i++) {
                        System.out.println(i+1 + ". " + songs.get(i).getName());
                    }
                    System.out.println("CHOICE: ");
                    songService.delete(songs.get(scan2.nextInt() - 1));
                }
                case 6 -> {
                    System.out.println("Do you want to delete all the songs of deleted vocalist? (1. Yes, 2. No)");
                    System.out.println("CHOICE:" );
                    int songsDeletion = scan1.nextInt();

                    System.out.println("Choose the vocalist to delete (number):");
                    List<Vocalist> vocalists = vocalistService.findAll();
                    for(int i = 0; i < vocalists.size(); i++) {
                        System.out.println(i+1 + ". " + vocalists.get(i).getName() + " " + vocalists.get(i).getSurname());
                    }
                    System.out.println("CHOICE: ");
                    Vocalist vocalist = vocalists.get(scan2.nextInt() - 1);
                    List<Song> songs = songService.findAll();
                    for(int i = 0; i < songs.size(); i++) {
                        if(songs.get(i).getVocalist().getName().equals(vocalist.getName())) {
                            if(songsDeletion == 1) {
                                songService.delete(songs.get(i));
                            }
                            else {
                                songs.get(i).setVocalist(null);
                                songService.update(songs.get(i));
                            }
                        }
                    }
                    vocalistService.delete(vocalist);
                }
                case 7 -> running = false;
                default -> System.out.println("Invalid action selected");
            }
        }
    }
}
