package services.authservices.service.implementation;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import services.authservices.dto.ChangePasswordDTO;
import services.authservices.dto.RequestRestartDTO;
import services.authservices.dto.RestartPasswordDTO;
import services.authservices.model.UserInfo;
import services.authservices.model.dto.AuthDTO;
import services.authservices.model.dto.RegistrationDTO;
import services.authservices.model.dto.UserResponseDTO;
import services.authservices.repository.AuthRepository;
import services.authservices.security.TokenUtils;
import services.authservices.service.IAuthService;
import services.authservices.service.IEmailService;

@Service
public class AuthService implements IAuthService, UserDetailsService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils token;
    private final IEmailService emailService;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, TokenUtils token, IEmailService emailService) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.token = token;
        this.emailService = emailService;
    }

    @Override
    public UserResponseDTO login(AuthDTO authDTO) {

        UserInfo user = authRepository.findOneByUsername(authDTO.getUsername());
        if (user == null) {
            return null;
        }
        String jwt = token.generateToken(user);
        int expiresIn = token.getEXPIRES_IN();

        UserResponseDTO userResponse = new UserResponseDTO(user,jwt);
        userResponse.setTokenExpiresIn(expiresIn);
        if(passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            return userResponse;
        }
        else{
            return null;
        }
    }

    @Override
    public boolean registration(RegistrationDTO registrationDTO) {
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        UserInfo userInfo = new UserInfo(registrationDTO);
        authRepository.save(userInfo);
        return true;
    }

    @Override
    public void changePasswordPatient(Long id, ChangePasswordDTO request) {
        UserInfo user = authRepository.findOneById(id);
        if(passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            if(request.getNewPassword().equals(request.getRePassword())){
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                authRepository.save(user);
            }
        }
    }



    @Override
    public void requestRestartPassword(RequestRestartDTO request) {

        UserInfo user = authRepository.findOneByEmail(request.getEmail());
        emailService.restartPassword(user);
    }

    @Override
    public void restartPassword(Long id, RestartPasswordDTO request) {
        UserInfo user = authRepository.findOneById(id);
            if(request.getNewPassword().equals(request.getRePassword())){
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                authRepository.save(user);
            }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo user = findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username '%s' was not found", s));
        } else {
            return user;
        }
    }

    public UserInfo findByUsername(String username) {
        return authRepository.findOneByUsername(username);
    }
}
