package francescoandrisani.u5w2d3.repositories;
import francescoandrisani.u5w2d3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDAO extends JpaRepository<Author, Integer> {
}
