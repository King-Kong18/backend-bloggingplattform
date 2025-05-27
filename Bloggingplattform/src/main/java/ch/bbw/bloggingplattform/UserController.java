package ch.bbw.bloggingplattform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public ResponseEntity<List<BlogUser>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body((List<BlogUser>) userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<BlogUser> getUser(@PathVariable long id) {
        Optional<BlogUser> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<BlogUser> deleteUser(@PathVariable long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/users")
    ResponseEntity<Iterable<BlogUser>> deleteAllUsers() {
        userRepository.deleteAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<BlogUser> createUser(@RequestBody BlogUser newUser) {
        newUser.setPassword(newUser.getPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.save(newUser));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<BlogUser> updateTaskUser(@PathVariable long id, @RequestBody BlogUser newUser) {
        Optional<BlogUser> currentUser = userRepository.findById(id);

        return currentUser
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userRepository.save(user));
                }).orElseGet(() -> {
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userRepository.save(newUser));
                });
    }
}
