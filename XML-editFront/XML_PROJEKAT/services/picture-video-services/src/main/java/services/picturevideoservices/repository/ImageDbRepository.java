package services.picturevideoservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.picturevideoservices.model.Image;

public interface ImageDbRepository extends JpaRepository<Image,Integer> {
    Image save(Image image);
}
