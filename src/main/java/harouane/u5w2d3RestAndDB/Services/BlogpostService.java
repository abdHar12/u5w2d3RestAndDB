package harouane.u5w2d3RestAndDB.Services;

import harouane.u5w2d3RestAndDB.Entities.Blogpost;
import harouane.u5w2d3RestAndDB.Entities.BlogpostPayload;
import harouane.u5w2d3RestAndDB.Exceptions.NotFound;
import harouane.u5w2d3RestAndDB.Repositories.BlogpostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BlogpostService {
    @Autowired
    BlogpostsDAO blogpostsDAO;
    @Autowired
    AuthorService authorService;
    public Page<Blogpost> findAll(int pageNumber, int size, String orderBy){
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogpostsDAO.findAll(pageable);
    }
    public Blogpost saveNewBlogpost(BlogpostPayload blogpostPayload){
        Blogpost blogpost=new Blogpost(blogpostPayload.getCategory(), blogpostPayload.getTitle(), blogpostPayload.getCover(), blogpostPayload.getContent(), blogpostPayload.getTimeOfReading(), authorService.findById(blogpostPayload.getAuthorId()));
        blogpostsDAO.save(blogpost);
        return blogpost;
    }

    public Blogpost findById(int id){
        return blogpostsDAO.findById(id).orElseThrow(()->new NotFound("L'id "+ id+ " non è presente"));
    }

    public Blogpost findByIdAndUpdate(int id, BlogpostPayload blogpostPayload) {
        Blogpost found = findById(id);
        found.setTitle(blogpostPayload.getTitle());
        found.setCategory(blogpostPayload.getCategory());
        found.setCover(blogpostPayload.getCover());
        found.setContent(blogpostPayload.getContent());
        found.setTimeOfReading(blogpostPayload.getTimeOfReading());
        found.setAuthor(authorService.findById(blogpostPayload.getAuthorId()));
        return blogpostsDAO.save(found);
    }

    public void findByIdAndDelete(int id){
        Blogpost found = findById(id);
        blogpostsDAO.delete(found);
    }
}
