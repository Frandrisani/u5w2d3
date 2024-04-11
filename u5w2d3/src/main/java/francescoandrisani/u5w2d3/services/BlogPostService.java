package francescoandrisani.u5w2d3.services;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import francescoandrisani.u5w2d3.entities.BlogPost;
import francescoandrisani.u5w2d3.payloads.NewPostDTO;
import francescoandrisani.u5w2d3.repositories.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlogPostService {

    @Autowired
    private BlogDAO blogDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;


    // ---------------------------------------------------
    // 1 - GET /blogPosts => ritorna la lista di blog post

    public Page<BlogPost> getBlogPosts() {
        Pageable pageable = PageRequest.of( 0,  50, Sort.by("title"));
         return this.blogDAO.findAll(pageable);
    }
    // ---------------------------------------------------

    // 2 - GET /blogPosts/id => ritorna il blog post con id specificato
    public BlogPost getBlogPost(int id) {
        return this.getBlogPost(id);
    }

    // ---------------------------------------------------

    // 3 - POST /blogPosts => inserisce un nuovo blog post
    public BlogPost addBlogPost(NewPostDTO body) {
        BlogPost newPost = new BlogPost(body.category(),body.title(), body.content(),body.timeForLecture(),"https://picsum.photos/200/300", body.author());
        return blogDAO.save(newPost);
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

    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }
}
