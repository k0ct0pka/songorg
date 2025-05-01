package entity.embeddableIds;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSongId {
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "song_id")
    private Integer songId;

}
