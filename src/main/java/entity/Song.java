package entity;

import entity.many_to_many_tables.AuthorSong;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "song")
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String link;
    private String genre;
    @OneToMany(mappedBy = "song")
    private List<AuthorSong> authorSongs;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
