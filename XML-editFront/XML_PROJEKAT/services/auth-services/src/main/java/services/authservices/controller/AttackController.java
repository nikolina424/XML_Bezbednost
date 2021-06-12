package services.authservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.authservices.service.IAttackService;
import services.authservices.service.implementation.AttackService;

@RestController
@RequestMapping(value = "/attack")
public class AttackController {

    @Autowired
    IAttackService attackService;

    //public AttackController

    @PostMapping("/email")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public ResponseEntity<?> validateEmail(@RequestBody String input){
        return new ResponseEntity<>(attackService.emailValidation(input), HttpStatus.OK);
    }

    @PostMapping("/password")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public ResponseEntity<?> validatePassword(@RequestBody String input){
        return new ResponseEntity<>(attackService.passwordValidation(input), HttpStatus.OK) ;
    }

    @PostMapping("/name")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public ResponseEntity<?> validateName(@RequestBody String input){
        return new ResponseEntity<>(attackService.nameValidation(input), HttpStatus.OK);
    }

    @PostMapping("/escape")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public ResponseEntity<?> charachterEscaping(@RequestBody String input){
        return new ResponseEntity<>(attackService.escaping(input), HttpStatus.OK);
    }

    @PostMapping("/organisation")
    public ResponseEntity<?> validateOrganisation(@RequestBody String input){
        return new ResponseEntity<>(attackService.organisationValidation(input), HttpStatus.OK);
    }

    @PostMapping("/phoneNumber")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public ResponseEntity<?> validatePhone(@RequestBody String input){
        return new ResponseEntity<>(attackService.phoneNumberValidation(input), HttpStatus.OK);
    }

    @PostMapping("/date")
    public ResponseEntity<?> validateDate(@RequestBody String input){
        return new ResponseEntity<>(attackService.organisationValidation(input), HttpStatus.OK);
    }

}