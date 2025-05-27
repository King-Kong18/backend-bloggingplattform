package ch.bbw.bloggingplattform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class BlogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "blogUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Blog> blogs;

    public BlogUser(String username, String hashCode) {
        this.username = username;
        hashPassword(hashCode);
    }

    public void hashPassword(String password) {
        generateSHA512Hash(password);
    }

    public void generateSHA512Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            String hash = Base64.getEncoder().encodeToString(hashedBytes);
            this.password = hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 not supported", e);
        }
    }

    public String getPassword() {
        generateSHA512Hash(password);
        return password;
    }
}


