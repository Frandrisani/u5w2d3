package francescoandrisani.u5w2d3.services;
import francescoandrisani.u5w2d3.entities.BlogPost;
import francescoandrisani.u5w2d3.exceptions.NotFound;
import francescoandrisani.u5w2d3.repositories.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogPostService {

    @Autowired
    private BlogDAO blogDAO;

    // ---------------------------------------------------
    // 1 - GET /blogPosts => ritorna la lista di blog post

    public Page<BlogPost> getBlogPosts() {
        Pageable pageable = PageRequest.of( 10,  10, Sort.Direction.valueOf("category"));
         return this.blogDAO.findAll(pageable);
    }
    // ---------------------------------------------------

    // 2 - GET /blogPosts/id => ritorna il blog post con id specificato
    public BlogPost getBlogPost(int id) {
        return this.blogDAO.findById(id).orElseThrow(() -> new NotFound(id));
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
        BlogPost post = this.getBlogPost(id);
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        post.setTimeForLecture(updatedPost.getTimeForLecture());
        return this.blogDAO.save(post);

    }
    // ---------------------------------------------------

    // 5 - DELETE /blogPosts/id => elimina il blog post con id specificato
    public void findByIdAndDelete(int id) {
        BlogPost found = this.getBlogPost(id);
        this.blogDAO.delete(found);

    }
}
