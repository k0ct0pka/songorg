package entity.many_to_many_tables;

import entity.Author;
import entity.Song;
import entity.User;
import entity.embeddableIds.UserSongId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "\"UserSong\"")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSong {
    @EmbeddedId
    UserSongId userSongId;
    @MapsId("userId")
    @ManyToOne( targetEntity = User.class)
    @JoinColumn(name = "user_id")
    User user;

    @MapsId("songId")
    @ManyToOne(targetEntity = Song.class)
    @JoinColumn(name = "song_id")
    Song song;
}
