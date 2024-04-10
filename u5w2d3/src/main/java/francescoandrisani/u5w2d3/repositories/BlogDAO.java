package francescoandrisani.u5w2d3.repositories;
import francescoandrisani.u5w2d3.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogDAO extends JpaRepository<BlogPost, Integer> {


}
