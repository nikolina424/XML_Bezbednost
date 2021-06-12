package services.authservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.authservices.model.UserInfo;

public interface AuthRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findOneByUsername(String username);

    UserInfo findOneById(Long id);

    UserInfo findOneByEmail(String email);
}
