package francescoandrisani.u5w2d3.controllers;
import francescoandrisani.u5w2d3.entities.BlogPost;
import francescoandrisani.u5w2d3.payloads.PostPayload;
import francescoandrisani.u5w2d3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogPostController {

    @Autowired
    public BlogPostService blogPostService;

    // GET - LISTA DEI POST
    @GetMapping
    public Page<BlogPost> getAllBlogPosts() {

        return this.blogPostService.getBlogPosts();
    }

    // GET - POST PER ID
    @GetMapping("/{id}")
    public BlogPost getBlogPost(@PathVariable("id") int id) {
        return this.blogPostService.getBlogPost(id);
    }

    // POST - INSERISCE UN NUOVO POST CON L'ID HENERATO AUTOMATICAMENTE TRAMITE IL SERVICE
    @PostMapping
    public BlogPost saveNewPost(@RequestBody PostPayload body){
        return this.blogPostService.addBlogPost(body);
    }

    // PUT - AGGIORNA IL POST CON ID SPECIFICATO
    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable("id") int id, @RequestBody BlogPost updatedPost) {
        return this.blogPostService.findByIdAndUpdate(id, updatedPost);
    }

    // DELETE - - ELIMINA TRAMITE ID UN POST
    @DeleteMapping("/{id}")
    public void deletePostBlog(@PathVariable("id") int id){
        this.blogPostService.findByIdAndDelete(id);
    }



}
