package harouane.u5w2d3RestAndDB.Controllers;

import harouane.u5w2d3RestAndDB.Entities.Blogpost;
import harouane.u5w2d3RestAndDB.Entities.BlogpostModel;
import harouane.u5w2d3RestAndDB.Services.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogpostController {
    @Autowired
    BlogpostService blogpostService;

    @GetMapping
    public List<Blogpost> getAllBlogs(){
        return blogpostService.findAll();
    }

    @PostMapping
    public Blogpost saveNewBlogs(@RequestBody BlogpostModel blogpost){
        return blogpostService.saveNewBlogpost(blogpost);
    }
    @GetMapping("/{id}")
    public Blogpost getSingleBlogpost(@PathVariable int id){
        return blogpostService.findById(id);
    }

    @PutMapping("/{id}")
    public Blogpost modifyPost(@PathVariable int id, @RequestBody BlogpostModel blogpost){
        return blogpostService.findByIdAndUpdate(id, blogpost);
    }

    @DeleteMapping("/{id}")
    public void removePost(@PathVariable int id){
        blogpostService.findByIdAndDelete(id);
    }
}
