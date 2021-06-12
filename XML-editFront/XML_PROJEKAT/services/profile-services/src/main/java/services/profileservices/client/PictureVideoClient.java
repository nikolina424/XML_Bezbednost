package services.profileservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import services.profileservices.dto.ImageDTO;

@FeignClient(name="pictureVideo",url="${app.pictureVideo.url}")
public interface PictureVideoClient {
    @PostMapping("image/upload")
    Integer uploadImage(@RequestBody ImageDTO imageDTO);
}
