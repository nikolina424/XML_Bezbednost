package services.authservices.service;

import services.authservices.dto.ChangePasswordDTO;
import services.authservices.dto.RequestRestartDTO;
import services.authservices.dto.RestartPasswordDTO;
import services.authservices.model.dto.AuthDTO;
import services.authservices.model.dto.RegistrationDTO;
import services.authservices.model.dto.UserResponseDTO;

public interface IAuthService {

    UserResponseDTO login(AuthDTO authDTO);
    boolean registration(RegistrationDTO registrationDTO);
    void changePasswordPatient(Long id, ChangePasswordDTO request);
    void requestRestartPassword(RequestRestartDTO request);
    void restartPassword(Long id, RestartPasswordDTO request);
}
