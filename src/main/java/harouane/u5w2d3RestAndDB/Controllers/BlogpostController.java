package harouane.u5w2d3RestAndDB.Controllers;

import harouane.u5w2d3RestAndDB.Entities.Blogpost;
import harouane.u5w2d3RestAndDB.Entities.BlogpostPayload;
import harouane.u5w2d3RestAndDB.Services.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogpostController {
    @Autowired
    BlogpostService blogpostService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Blogpost> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy){
        return blogpostService.findAll(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blogpost saveNewBlogs(@RequestBody BlogpostPayload blogpost){
        return blogpostService.saveNewBlogpost(blogpost);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Blogpost getSingleBlogpost(@PathVariable int id){
        return blogpostService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Blogpost modifyPost(@PathVariable int id, @RequestBody BlogpostPayload blogpost){
        return blogpostService.findByIdAndUpdate(id, blogpost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removePost(@PathVariable int id){
        blogpostService.findByIdAndDelete(id);
    }
}
