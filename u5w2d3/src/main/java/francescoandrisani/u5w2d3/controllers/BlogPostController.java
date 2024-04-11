package francescoandrisani.u5w2d3.controllers;
import francescoandrisani.u5w2d3.entities.BlogPost;
import francescoandrisani.u5w2d3.exceptions.BadRequestExceptions;
import francescoandrisani.u5w2d3.payloads.NewPostDTO;
import francescoandrisani.u5w2d3.payloads.NewPostRispostaDTO;
import francescoandrisani.u5w2d3.services.BlogPostService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public NewPostRispostaDTO saveNewPost(@RequestBody @Validated NewPostDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestExceptions(validation.getAllErrors());
        }
        return new NewPostRispostaDTO(this.blogPostService.addBlogPost(body).getId());
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

    @PostMapping("/upload")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image) throws IOException {
        // "avatar" deve corrispondere ESATTAMENTE alla chiave del Multipart dove sarà contenuto il file
        // altrimenti il file non verrà trovato
        return this.blogPostService.uploadImage(image);

    }

}
