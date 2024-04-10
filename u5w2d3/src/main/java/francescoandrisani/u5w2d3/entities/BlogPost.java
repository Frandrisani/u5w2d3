package francescoandrisani.u5w2d3.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue
    private int id;
    private String category;
    private String title;
    private String content;
    private int timeForLecture;
    private String cover;
    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public BlogPost(int id, String category, String title, String content, int timeForLecture, String cover) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.timeForLecture = timeForLecture;
        this.cover = cover;
    }

}
