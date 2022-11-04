package pl.edu.pg.eti.kask.musicService.element.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Song implements Serializable {

    private String name;
    private Vocalist vocalist;
    private String genre;
    private int releaseYear;
    private double length;
}
