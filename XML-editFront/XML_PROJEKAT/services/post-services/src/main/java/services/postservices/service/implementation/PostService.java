package services.postservices.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.postservices.client.PictureVideoClient;
import services.postservices.client.ProfileClient;
import services.postservices.dto.GeneralException;
import services.postservices.dto.ImageDTO;
import services.postservices.model.Post;
import services.postservices.model.PostInfo;
import services.postservices.repository.PostRepository;
import services.postservices.service.IPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final PictureVideoClient pictureVideoClient;
    private final ProfileClient profileClient;
    private final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository, PictureVideoClient pictureVideoClient, ProfileClient profileClient){this.postRepository = postRepository;
        this.pictureVideoClient = pictureVideoClient;
        this.profileClient = profileClient;
    }

    @Override
    public int save(MultipartFile multipartFile, String location, String caption, String userInfoId) throws IOException {
        Post p = new Post();
        int contentId = pictureVideoClient.uploadImage(new ImageDTO(multipartFile.getOriginalFilename(),multipartFile.getBytes()));
        PostInfo postInfo = new PostInfo();
        List<Integer> ids = new ArrayList<>();
        ids.add(contentId);
        postInfo.setPictureIds(ids);
        postInfo.setCaption(caption);
        postInfo.setLocation(location);
        postInfo.setDate(LocalDate.now());
        p.setPostInfo(postInfo);
        Post new_post = postRepository.save(p);
        int userId = Integer.parseInt(userInfoId);
        profileClient.addPost(new_post.getId(), userId);
        return new_post.getId();
    }

    private void checkSQLInjectionLogin(String location, String caption)throws GeneralException {
        if(!SqlSafeUtil.isSqlInjectionSafe(location)){
            logger.debug("Post location field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(caption)){
            logger.debug("Post caption field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
    }
}
