package francescoandrisani.u5w2d3.services;
import francescoandrisani.u5w2d3.entities.Author;
import francescoandrisani.u5w2d3.exceptions.NotFound;
import francescoandrisani.u5w2d3.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    // ---------------------------------------------------

    // 1 - GET /authors => ritorna la lista di autori
    public Page<Author> getAuthors(){
        Pageable pageable = PageRequest.of( 0,  50, Sort.by("name"));
        return this.authorDAO.findAll(pageable);
    }
    // ---------------------------------------------------

    // 2 - GET /authors/123 => ritorna un singolo autore
    public Author getAuthorById(int id){
        return this.authorDAO.findById(id).orElseThrow(() -> new NotFound(id));
    }
    // ---------------------------------------------------

    // 3 - POST /authors => crea un nuovo autore
    public Author creaNuovoAuthor(@RequestBody Author body){
        Random random = new Random();
        body.setId(random.nextInt(1,100));
        body.setAvatar( "https://ui-avatars.com/api/?name="+ body.getName() + "+" + body.getSurname());
        return authorDAO.save(body);
    }
    // ---------------------------------------------------

    // 4 - PUT /blogPosts/id => modifica il blog post con id specificato
    public Author findByIdAndUpdate(int id, Author updatedAut){
        Author author = this.getAuthorById(id);
        author.setName(updatedAut.getName());
        author.setSurname(updatedAut.getSurname());
        author.setEmail(updatedAut.getEmail());
        author.setBirthDate(updatedAut.getBirthDate());
        return this.authorDAO.save(author);
    }
    // ---------------------------------------------------

    // 5 - DELETE /blogPosts/id => elimina il blog post con id specificato
    public void findByIdAndDelete(int id) {
        Author found = this.getAuthorById(id);
        this.authorDAO.delete(found);
    }
    // ---------------------------------------------------
}
