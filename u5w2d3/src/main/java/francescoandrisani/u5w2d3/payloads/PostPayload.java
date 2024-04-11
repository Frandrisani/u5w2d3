package francescoandrisani.u5w2d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PostPayload {
    private String category;
    private String title;
    private String content;
    private int timeForLecture;
    private int Author_id;
    @Setter
    private String cover;

}
