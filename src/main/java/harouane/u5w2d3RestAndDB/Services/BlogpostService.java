package harouane.u5w2d3RestAndDB.Services;

import harouane.u5w2d3RestAndDB.Entities.Blogpost;
import harouane.u5w2d3RestAndDB.Entities.BlogpostModel;
import harouane.u5w2d3RestAndDB.Exceptions.NotFoundAnyElement;
import harouane.u5w2d3RestAndDB.Repositories.BlogpostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BlogpostService {
    @Autowired
    BlogpostsDAO blogpostsDAO;
    @Autowired
    AuthorService authorService;
    public List<Blogpost> findAll(){
        return blogpostsDAO.findAll();
    }
    public Blogpost saveNewBlogpost(BlogpostModel blogpostModel){
        Blogpost blogpost=new Blogpost(blogpostModel.getCategory(), blogpostModel.getTitle(), blogpostModel.getCover(), blogpostModel.getContent(), blogpostModel.getTimeOfReading(), authorService.findById(blogpostModel.getAuthorId()));
        blogpostsDAO.save(blogpost);
        return blogpost;
    }

    public Blogpost findById(int id){
        return blogpostsDAO.findById(id).orElseThrow(()->new NotFoundAnyElement("L'id "+ id+ " non è presente"));
    }

    public Blogpost findByIdAndUpdate(int id, BlogpostModel blogpostModel) {
        Blogpost found = findById(id);
        found.setTitle(blogpostModel.getTitle());
        found.setCategory(blogpostModel.getCategory());
        found.setCover(blogpostModel.getCover());
        found.setContent(blogpostModel.getContent());
        found.setTimeOfReading(blogpostModel.getTimeOfReading());
        found.setAuthor(authorService.findById(blogpostModel.getAuthorId()));
        return blogpostsDAO.save(found);
    }

    public void findByIdAndDelete(int id){
        Blogpost found = findById(id);
        blogpostsDAO.delete(found);
    }
}
