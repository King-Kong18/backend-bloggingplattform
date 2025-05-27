package ch.bbw.bloggingplattform;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BlogsRestController {

    static Logger log = Logger.getAnonymousLogger();

    private final BlogRepository blogsRepositoryDB;

    public BlogsRestController(BlogRepository taskRepositoryDb) {
        this.blogsRepositoryDB = taskRepositoryDb;
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getBlogs() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body((List<Blog>) blogsRepositoryDB.findAll());
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable long id) {
        Optional<Blog> blog = blogsRepositoryDB.findById(id);
        return blog.map(value -> ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/blogs/{id}")
    ResponseEntity<Blog> deleteBlog(@PathVariable long id) {
        if (blogsRepositoryDB.findById(id).isPresent()) {
            blogsRepositoryDB.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/blogs")
    ResponseEntity<Iterable<Blog>> deleteAllBlogs() {
        blogsRepositoryDB.deleteAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(blogsRepositoryDB.findAll());
    }

    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog newBlog) {
        log.info(newBlog.toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(blogsRepositoryDB.save(newBlog));
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable long id, @RequestBody Blog newBlog) {
        Optional<Blog> currentBlog = blogsRepositoryDB.findById(id);

        log.info(currentBlog.toString());

        return currentBlog
                .map(blog -> {
                    blog.setTitle(newBlog.getTitle());
                    blog.setContent(newBlog.getContent());
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(blogsRepositoryDB.save(blog));
                }).orElseGet(() -> {
                    newBlog.setId(id);
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(blogsRepositoryDB.save(newBlog));
                });
    }
}