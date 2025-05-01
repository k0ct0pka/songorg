package entity;

import java.util.*;

import entity.many_to_many_tables.AuthorSong;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "Author")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(name = "concerts_link")
    String concertsLink;
    @OneToMany(mappedBy = "author")
    List<AuthorSong> authorSongs;

}
