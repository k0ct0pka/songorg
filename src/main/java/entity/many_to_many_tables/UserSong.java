package entity.many_to_many_tables;

import entity.Author;
import entity.Song;
import entity.User;
import entity.embeddableIds.UserSongId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "\"UserSong\"")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSong {
    @EmbeddedId
    UserSongId userSongId;
    @MapsId("userId")
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = User.class)
    User user;

    @MapsId("songId")
    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = Song.class)
    Song song;
}
