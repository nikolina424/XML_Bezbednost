package services.profileservices.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.profileservices.model.ProfileCategory;
import services.profileservices.model.VerificationRequest;
import services.profileservices.service.IVerificationRequestService;

import java.util.List;

@RestController
@RequestMapping("/verificationRequest")
public class VerificationRequestController {
    private final IVerificationRequestService verificationRequestService;

    public VerificationRequestController(IVerificationRequestService verificationRequestService){
        this.verificationRequestService = verificationRequestService;
    }

    @PostMapping("/create")
    public Integer createVerification(@RequestParam("file") MultipartFile multipartFile, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("category") String category) throws Exception{
        return verificationRequestService.save(multipartFile, name, surname, ProfileCategory.valueOf(category));
    }

    @GetMapping("/getAll")
    public List<VerificationRequest> getAll(){
        return verificationRequestService.getAll();
    }
}
