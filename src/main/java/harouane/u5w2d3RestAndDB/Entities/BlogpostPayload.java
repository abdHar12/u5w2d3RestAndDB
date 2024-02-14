package harouane.u5w2d3RestAndDB.Entities;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogpostPayload {
    private String category;
    private String title;
    private String cover;
    private String content;
    private int timeOfReading;
    private int authorId;
}
