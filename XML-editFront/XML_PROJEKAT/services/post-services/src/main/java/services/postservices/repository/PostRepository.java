package services.postservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.postservices.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post save(Post post);
    Post findOneById(int id);
}
