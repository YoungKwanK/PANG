package org.PANG.post.repository.entity;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.PANG.common.domain.PositiveIntegerCounter;
import org.PANG.post.domain.Content;
import org.PANG.post.domain.Post;
import org.PANG.post.domain.PostInfo;
import org.PANG.post.domain.PostState;
import org.PANG.user.repository.entity.UserEntity;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;
    private String title;
    private String content;
    private String category;
    private double latitude;
    private double longitude;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int max_users;
    private int current_users;
    private int age;

    @Convert(converter = PostStateConverter.class)
    private PostState state;


    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.title = post.getTitle();
        this.content = post.getContentText();
        this.category = post.getCategory();
        this.latitude = post.getLocation().getLatitude();
        this.longitude = post.getLocation().getLongitude();
        this.start_time = post.getStart_time();
        this.end_time = post.getEnd_time();
        this.max_users = post.getMax_users();
        this.current_users = post.getCurrent_users();
        this.age = post.getAge();
        this.state = post.getPostState();
    }

    public Post toPost() {
        return Post.builder()
            .id(id)
            .author(author.toUser())
            .content(new Content(title, content))
            .postInfo(
                new PostInfo(category, latitude, longitude, start_time, end_time, max_users,
                    new PositiveIntegerCounter(current_users), age,
                    state))
            .build();
    }
}
