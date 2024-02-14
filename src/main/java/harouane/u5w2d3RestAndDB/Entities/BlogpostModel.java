package harouane.u5w2d3RestAndDB.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogpostModel {
    private String category;
    private String title;
    private String cover;
    private String content;
    private int timeOfReading;
    private int authorId;
}
