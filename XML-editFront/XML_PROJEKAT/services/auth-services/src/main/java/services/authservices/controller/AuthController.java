package services.authservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.authservices.dto.ChangePasswordDTO;
import services.authservices.dto.RequestRestartDTO;
import services.authservices.dto.RestartPasswordDTO;
import services.authservices.model.dto.AuthDTO;
import services.authservices.model.dto.RegistrationDTO;
import services.authservices.model.dto.UserResponseDTO;
import services.authservices.service.IAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody RegistrationDTO registrationDTO){
        try{
            return new ResponseEntity(authService.registration(registrationDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO authDTO){
        try{
            return new ResponseEntity(authService.login(authDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}/change-password")
    public void changePassword(@PathVariable("id")Long id, @RequestBody ChangePasswordDTO request){
        authService.changePasswordPatient(id, request);
    }

    @PutMapping("/{id}/restart-password")
    public void restartPassword(@PathVariable("id")Long id, @RequestBody RestartPasswordDTO request){
        authService.restartPassword(id, request);
    }


    @PostMapping("/password")
    public void requestRestartPassword(@RequestBody RequestRestartDTO request){
        authService.requestRestartPassword(request);
    }

    @GetMapping("/getUserInfoId/{username}")
    public ResponseEntity getByUsername(@PathVariable String username){
        try{
            return new ResponseEntity(authService.getByUsername(username), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
