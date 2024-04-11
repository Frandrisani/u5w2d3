package francescoandrisani.u5w2d3.payloads;
import francescoandrisani.u5w2d3.entities.Author;


public record NewPostDTO(String category, String title, String content, int timeForLecture, String cover, Author author) {

}
