package harouane.u5w2d3RestAndDB.Controllers;

import harouane.u5w2d3RestAndDB.Entities.Author;
import harouane.u5w2d3RestAndDB.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.findAll();
    }
    @PostMapping
    public Author saveNewAuthor(@RequestBody Author author){
        return authorService.saveNewAuthor(author);
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable int id){
        return authorService.findById(id);
    }

    @PutMapping("/{id}")
    public Author modifyAuthor(@PathVariable int id, @RequestBody Author modifiedAuthor){
        return authorService.findByIdAndUpdate(id, modifiedAuthor);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.findByIdAndDelete(id);
    }
}
