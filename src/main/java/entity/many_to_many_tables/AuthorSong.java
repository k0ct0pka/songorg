package entity.many_to_many_tables;

import entity.Author;
import entity.Song;
import entity.embeddableIds.AuthorSongId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "\"AuthorSong\"")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorSong {
    @EmbeddedId
    AuthorSongId authorSongId;

    @MapsId("authorId")
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = Author.class)
    Author author;

    @MapsId("songId")
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = Song.class)
    Song song;

}
