package services.profileservices.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.profileservices.client.PictureVideoClient;
import services.profileservices.dto.ImageDTO;
import services.profileservices.model.ProfileCategory;
import services.profileservices.model.VerificationRequest;
import services.profileservices.repository.VerificationRequestRepository;
import services.profileservices.service.IVerificationRequestService;

import java.io.IOException;
import java.util.List;

@Service
public class VerificationRequestService implements IVerificationRequestService {
    private final VerificationRequestRepository verificationRequestRepository;
    private final PictureVideoClient pictureVideoClient;

    public VerificationRequestService(VerificationRequestRepository verificationRequestRepository, PictureVideoClient pictureVideoClient){
        this.verificationRequestRepository=verificationRequestRepository;
        this.pictureVideoClient = pictureVideoClient;
    }

    @Override
    public int save(MultipartFile multipartFile, String name, String surname, ProfileCategory category) throws IOException {
        int officialDocument = pictureVideoClient.uploadImage(new ImageDTO(multipartFile.getOriginalFilename(),multipartFile.getBytes()));
        VerificationRequest verificationRequest = new VerificationRequest(name, surname, category, officialDocument);
        return verificationRequestRepository.save(verificationRequest).getId();
    }

    @Override
    public List<VerificationRequest> getAll() {
        return verificationRequestRepository.findAll();
    }
}
