package services.profileservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.profileservices.model.VerificationRequest;

import java.util.List;


@Repository
public interface VerificationRequestRepository extends JpaRepository<VerificationRequest, Integer> {
    VerificationRequest save(VerificationRequest verificationRequest);
    List<VerificationRequest> findAll();
}
