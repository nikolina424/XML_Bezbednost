package services.picturevideoservices.service.implementation;

import org.springframework.stereotype.Service;
import services.picturevideoservices.model.PictureVideo;
import services.picturevideoservices.repository.PictureVideoRepository;
import services.picturevideoservices.service.IPictureVideoService;

import java.util.List;

@Service
public class PictureVideoService implements IPictureVideoService {

    private final PictureVideoRepository pictureVideoRepository;

    public PictureVideoService(PictureVideoRepository pictureVideoRepository){this.pictureVideoRepository = pictureVideoRepository;}

    @Override
    public PictureVideo save(PictureVideo pictureVideo) {
        return pictureVideoRepository.save(pictureVideo);
    }

    @Override
    public List<PictureVideo> getAll() {
        return pictureVideoRepository.findAll();
    }

    @Override
    public PictureVideo getById(int id) {
        return null;
    }
}
