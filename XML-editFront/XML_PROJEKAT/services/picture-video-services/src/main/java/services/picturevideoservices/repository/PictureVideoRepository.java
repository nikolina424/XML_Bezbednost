package services.picturevideoservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.picturevideoservices.model.PictureVideo;

import java.util.List;

public interface PictureVideoRepository extends JpaRepository<PictureVideo, Integer> {
    PictureVideo save(PictureVideo pictureVideo);
    PictureVideo findOneById(int id);
    PictureVideo findOneByPath(String path);
    List<PictureVideo> findAll();
}
