package entity.embeddableIds;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSongId {
    private Integer authorId;
    private Integer songId;

}
