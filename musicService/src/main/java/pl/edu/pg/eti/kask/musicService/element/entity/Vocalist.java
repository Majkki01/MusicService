package pl.edu.pg.eti.kask.musicService.element.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Vocalist implements Serializable {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private int albumsCount;
    private String recordCompany;

}
