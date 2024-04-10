package francescoandrisani.u5w2d3.controllers;
import francescoandrisani.u5w2d3.entities.Author;
import francescoandrisani.u5w2d3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    public AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors(){
        return this.authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") int id){
        return this.authorService.getAuthorById(id);
    }

    @PostMapping
    public Author saveNewAuthor(@RequestBody Author body){
        return this.authorService.creaNuovoAuthor(body);
    }
}
