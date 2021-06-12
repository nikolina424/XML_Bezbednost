package services.postservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ElementCollection
    @CollectionTable(name="Post_Likes", joinColumns=@JoinColumn(name="Post_ID"))
    @Column(name="like")
    private List<Integer> likeIds;
    @ElementCollection
    @CollectionTable(name="Post_Dislikes", joinColumns=@JoinColumn(name="Post_ID"))
    @Column(name="dislike")
    private List<Integer> dislikeIds;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PostInfo postInfo;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Post_ID")
    private List<Comment> comments;
}
