package services.authservices.service;

import services.authservices.dto.ResponseDTO;

public interface IAttackService {

    ResponseDTO escaping(String input);
    ResponseDTO emailValidation(String input);
    ResponseDTO passwordValidation(String input);
    ResponseDTO nameValidation(String input);
    ResponseDTO organisationValidation(String input);
    ResponseDTO phoneNumberValidation(String input);
    ResponseDTO dateValidation(String input);
}
