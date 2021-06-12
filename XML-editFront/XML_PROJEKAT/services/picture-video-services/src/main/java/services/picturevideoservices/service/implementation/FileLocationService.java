package services.picturevideoservices.service.implementation;

import org.springframework.stereotype.Service;
import services.picturevideoservices.model.Image;
import services.picturevideoservices.repository.FileSystemRepository;
import services.picturevideoservices.repository.ImageDbRepository;

import java.util.Date;

@Service
public class FileLocationService {

    private final FileSystemRepository fileSystemRepository;
    private final ImageDbRepository imageDbRepository;

    public FileLocationService(FileSystemRepository fileSystemRepository, ImageDbRepository imageDbRepository){this.fileSystemRepository = fileSystemRepository; this.imageDbRepository = imageDbRepository;}

    public Integer save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);

        return imageDbRepository.save(new Image(new Date().getTime() + "-" + imageName, location)).getId();
    }
}
