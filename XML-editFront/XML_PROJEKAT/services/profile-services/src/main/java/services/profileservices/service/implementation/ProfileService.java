package services.profileservices.service.implementation;

import org.springframework.stereotype.Service;
import services.profileservices.model.Profile;
import services.profileservices.repository.ProfileRepository;
import services.profileservices.service.IProfileService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository){this.profileRepository = profileRepository;}

    @Override
    public Boolean createProfile(int userInfoId) {
        Profile profile = new Profile();
        profile.setUserInfoId(userInfoId);
        profile.setIsPrivate(false);
        profile.setCanBeMessaged(true);
        profile.setCanBeTagged(true);
        profile.setPostIds(new ArrayList<>());
        profile.setStoryIds(new ArrayList<>());
        profile.setFollowing(new ArrayList<>());
        profile.setFollowers(new ArrayList<>());
        profile.setFriends(new ArrayList<>());
        profile.setBlocked(new ArrayList<>());
        profile.setMuted(new ArrayList<>());
        Profile savedProfile = profileRepository.save(profile);
        if(savedProfile != null){
            return true;
        }else return false;
    }

    @Override
    public Boolean addPost(int postId, int userInfoId) {
        Profile profile = profileRepository.findOneByUserInfoId(userInfoId);
        List<Integer> ids = profile.getPostIds();
        int old_ids_len = ids.size();
        ids.add(postId);
        profile.setPostIds(ids);
        int new_ids_len = profileRepository.save(profile).getPostIds().size();
        if(new_ids_len > old_ids_len)
            return true;
        else return false;
    }
}
