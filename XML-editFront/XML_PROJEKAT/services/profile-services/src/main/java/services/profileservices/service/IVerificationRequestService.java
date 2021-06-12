package services.profileservices.service;

import org.springframework.web.multipart.MultipartFile;
import services.profileservices.model.ProfileCategory;
import services.profileservices.model.VerificationRequest;

import java.io.IOException;
import java.util.List;

public interface IVerificationRequestService {
    int save(MultipartFile multipartFile, String name, String surname, ProfileCategory category) throws IOException;
    List<VerificationRequest> getAll();
}
