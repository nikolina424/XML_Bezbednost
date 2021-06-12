package services.profileservices.service;

public interface IProfileService {
    Boolean createProfile(int userInfoId);
    Boolean addPost(int postId, int userInfoId);
}
