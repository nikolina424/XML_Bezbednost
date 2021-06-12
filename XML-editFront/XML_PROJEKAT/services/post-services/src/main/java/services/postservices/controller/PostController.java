package services.postservices.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import services.postservices.service.IPostService;

@RestController
@RequestMapping("/post")
public class PostController {
    private final IPostService postService;
    public PostController(IPostService postService){this.postService = postService;}

    @PostMapping("/create")
    public Integer createPost(@RequestParam("file") MultipartFile multipartFile, @RequestParam("location") String location, @RequestParam("caption") String caption, @RequestParam("userInfoId") String userInfoId) throws Exception{
        return postService.save(multipartFile, location, caption, userInfoId);
    }
}
