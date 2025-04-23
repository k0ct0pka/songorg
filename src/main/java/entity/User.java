package entity;

import entity.embeddableIds.UserSongId;
import entity.many_to_many_tables.UserSong;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@Entity
@Table(name = "user", schema = "reverie")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column
    String password;
    @OneToMany(mappedBy = "user")
    List<UserSong> userSongs;
}
