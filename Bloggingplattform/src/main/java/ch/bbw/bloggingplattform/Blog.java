package ch.bbw.bloggingplattform;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "blog_user_id", nullable = false)
    private BlogUser blogUser;

    public Blog(String description, String content, BlogUser blogUser) {
        this.title = description;
        this.content = content;
        this.blogUser = blogUser;
    }
}

