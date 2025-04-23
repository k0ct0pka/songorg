package entity.embeddableIds;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Embeddable
public class AuthorSongId {
    private Integer authorId;
    private Integer songId;

}
