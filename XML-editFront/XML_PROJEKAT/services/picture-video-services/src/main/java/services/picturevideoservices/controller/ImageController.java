package services.picturevideoservices.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.picturevideoservices.dto.ImageDTO;
import services.picturevideoservices.service.implementation.FileLocationService;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final FileLocationService fileLocationService;

    public ImageController(FileLocationService fileLocationService){this.fileLocationService = fileLocationService;}

    @PostMapping("/upload")
    //Integer uploadImage(@RequestParam("file") MultipartFile multipartImage) throws Exception {
    Integer uploadImage(@RequestBody ImageDTO imageDTO) throws Exception {
        //return fileLocationService.save(multipartImage.getBytes(),multipartImage.getOriginalFilename());

        return fileLocationService.save(imageDTO.getContent(),imageDTO.getName());
    }
}
