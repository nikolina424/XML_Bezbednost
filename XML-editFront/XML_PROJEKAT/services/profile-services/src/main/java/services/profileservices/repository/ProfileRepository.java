package services.profileservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.profileservices.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile save(Profile profile);
    Profile findOneByUserInfoId(int id);
}
