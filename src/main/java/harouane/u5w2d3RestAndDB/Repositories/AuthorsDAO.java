package harouane.u5w2d3RestAndDB.Repositories;

import harouane.u5w2d3RestAndDB.Entities.Author;
import harouane.u5w2d3RestAndDB.Entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorsDAO extends JpaRepository<Author, Integer> {
    List<Author> findByEmail(String email);
    Optional<Author> findById(int id);

    List<Author> findAll();
}
