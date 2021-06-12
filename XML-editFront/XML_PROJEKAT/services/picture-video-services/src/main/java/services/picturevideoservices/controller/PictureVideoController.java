package services.picturevideoservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.picturevideoservices.model.PictureVideo;
import services.picturevideoservices.service.IPictureVideoService;

@RestController
@RequestMapping("/pictureVideo")
public class PictureVideoController {
    private final IPictureVideoService pictureVideoService;

    public PictureVideoController(IPictureVideoService pictureVideoService){this.pictureVideoService = pictureVideoService;}

    @GetMapping("/getById/{id}")
    public PictureVideo getById(@PathVariable int id){
        return pictureVideoService.getById(id);
    }
}
