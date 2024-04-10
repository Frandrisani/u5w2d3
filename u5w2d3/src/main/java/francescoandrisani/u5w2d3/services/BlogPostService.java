package francescoandrisani.u5w2d3.services;
import francescoandrisani.u5w2d3.entities.BlogPost;
import francescoandrisani.u5w2d3.exceptions.NotFound;
import francescoandrisani.u5w2d3.repositories.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class BlogPostService {

    @Autowired
    private BlogDAO blogDAO;

    // ---------------------------------------------------
    // 1 - GET /blogPosts => ritorna la lista di blog post
    private List<BlogPost> postList = new ArrayList<>();
    public List<BlogPost> getBlogPosts() {
        return this.postList;
    }
    // ---------------------------------------------------

    // 2 - GET /blogPosts/id => ritorna il blog post con id specificato
    public BlogPost getBlogPost(int id) {
        for (BlogPost post : this.postList) {
            if (post.getId() == id) {
                return post;
            }
        }
        throw new NotFound(id);
    }

    // ---------------------------------------------------

    // 3 - POST /blogPosts => inserisce un nuovo blog post
    public BlogPost addBlogPost(BlogPost body) {
        Random random = new Random();
        body.setId(random.nextInt(1,100));
        body.setCover("https://picsum.photos/200/300");
        return blogDAO.save(body);
    }
    // ---------------------------------------------------

    // 4 - PUT /blogPosts/id => modifica il blog post con id specificato
    public BlogPost findByIdAndUpdate(int id, BlogPost updatedPost){
        for (BlogPost post : this.postList) {
            if (post.getId() == id) {
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                post.setTimeForLecture(updatedPost.getTimeForLecture());
                return post;
            }
        }
        throw new NotFound(id);
    }
    // ---------------------------------------------------

    // 5 - DELETE /blogPosts/id => elimina il blog post con id specificato
    public void findByIdAndDelete(int id) {
        Iterator<BlogPost> iterator = this.postList.iterator();
        while (iterator.hasNext()) {
            BlogPost current = iterator.next();
            if (current.getId() == id) {
                iterator.remove();

            }

        }

    }
}
