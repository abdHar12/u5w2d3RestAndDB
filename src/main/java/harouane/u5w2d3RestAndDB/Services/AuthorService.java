package harouane.u5w2d3RestAndDB.Services;

import harouane.u5w2d3RestAndDB.Entities.Author;
import harouane.u5w2d3RestAndDB.Entities.Blogpost;
import harouane.u5w2d3RestAndDB.Exceptions.InvalidInsert;
import harouane.u5w2d3RestAndDB.Exceptions.NotFoundAnyElement;
import harouane.u5w2d3RestAndDB.Repositories.AuthorsDAO;
import harouane.u5w2d3RestAndDB.Repositories.BlogpostsDAO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorsDAO authorsDAO;
    @Autowired
    BlogpostsDAO blogpostsDAO;

    public List<Author> findAll(){
        return authorsDAO.findAll();
    }
     public Author saveNewAuthor(Author author){
         if(!authorsDAO.findByEmail(author.getEmail()).isEmpty()) throw new InvalidInsert("L'email inserità è gia presente");
         author.setAvatar("https://ui-avatars.com/api/?name"+author.getName()+"+"+author.getLastName());
         authorsDAO.save(author);
         return author;
     }

    public Author findById(int id) {
         Optional<Author> found= authorsDAO.findById(id);
         return found.orElseThrow(()->new NotFoundAnyElement("L'id "+ id+ " non è presente"));
    }

    public Author findByIdAndUpdate(int id, Author modifiedAuthor){
        Author found = findById(id);
        found.setAvatar(modifiedAuthor.getAvatar());
        found.setName(modifiedAuthor.getName());
        found.setLastName(modifiedAuthor.getLastName());
        found.setEmail(modifiedAuthor.getEmail());
        found.setDateOfBirth(modifiedAuthor.getDateOfBirth());
        return authorsDAO.save(found);
    }

    public void findByIdAndDelete(int id){
        Author found = findById(id);
        authorsDAO.delete(found);
    }
}
