package services.picturevideoservices.service;

import services.picturevideoservices.model.PictureVideo;

import java.util.List;

public interface IPictureVideoService {
    PictureVideo save(PictureVideo pictureVideo);
    List<PictureVideo> getAll();
    PictureVideo getById(int id);
}
