package entity.embeddableIds;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserSongId {
    private Integer userId;
    private Integer songId;
}
