package services.authservices.service.implementation;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.http.HttpStatus;
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
import services.authservices.model.dto.GeneralException;
import services.authservices.model.dto.RegistrationDTO;
import services.authservices.model.dto.UserResponseDTO;
import services.authservices.repository.AuthRepository;
import services.authservices.security.SqlSafeUtil;
import services.authservices.security.TokenUtils;
import services.authservices.service.IAuthService;
import services.authservices.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService implements IAuthService, UserDetailsService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils token;
    private final IEmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, TokenUtils token, IEmailService emailService) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.token = token;
        this.emailService = emailService;
    }

    @Override
    public UserResponseDTO login(AuthDTO authDTO) {

        checkSQLInjectionLogin(authDTO);
        UserInfo user = authRepository.findOneByUsername(authDTO.getUsername());
        if (user == null) {
            return null;
        }
        String jwt = token.generateToken(user);
        int expiresIn = token.getEXPIRES_IN();

        UserResponseDTO userResponse = new UserResponseDTO(user,jwt);
        userResponse.setTokenExpiresIn(expiresIn);
        if(passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            logger.info("User " + authDTO.getUsername() + " has successfully logged in");
            user.setLoginCounter(0);
            authRepository.save(user);
            return userResponse;
        }
        else{
            user.setLoginCounter(user.getLoginCounter()+1);
            authRepository.save(user);
            if(user.getLoginCounter() > 4)
            {
                logger.warn("User " + user.getUsername() + " has entered bad password " + user.getLoginCounter() + " times");
            }

            return null;
        }
    }

    @Override
    public boolean registration(RegistrationDTO registrationDTO) {
        checkSQLInjection(registrationDTO);
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        UserInfo userInfo = new UserInfo(registrationDTO);
        authRepository.save(userInfo);
        logger.info("User " + registrationDTO.getUsername() + " has successfully registered");
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
        checkSQLInjectionRequestRestart(request);
        UserInfo user = authRepository.findOneByEmail(request.getEmail());
        emailService.restartPassword(user);
        logger.info("A password restart email has been sent to " + request.getEmail());
    }

    @Override
    public void restartPassword(Long id, RestartPasswordDTO request) {
        checkSQLInjectionRestart(request);
        UserInfo user = authRepository.findOneById(id);
            if(request.getNewPassword().equals(request.getRePassword())){
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                authRepository.save(user);
                logger.info("User" + user.getUsername() + "has successfully restarted their password");
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

    private void checkSQLInjection(RegistrationDTO registrationDTO)throws GeneralException {
        if(!SqlSafeUtil.isSqlInjectionSafe(registrationDTO.getUsername())) {
            logger.debug("Username field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(registrationDTO.getPassword())){
            logger.debug("Password field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(registrationDTO.getSurname())){
            logger.debug("Surname was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(registrationDTO.getName())){
            logger.debug("Name field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(registrationDTO.getEmail())){
            logger.debug("Email field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(registrationDTO.getBirthday())){
            logger.debug("Birthday number was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkSQLInjectionLogin(AuthDTO authDTO)throws GeneralException{
        if(!SqlSafeUtil.isSqlInjectionSafe(authDTO.getUsername())){
            logger.debug("Login username field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(authDTO.getPassword())){
            logger.debug("Login password field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkSQLInjectionRequestRestart(RequestRestartDTO request)throws GeneralException{
        if(!SqlSafeUtil.isSqlInjectionSafe(request.getEmail())){
            logger.debug("Request restart email field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkSQLInjectionRestart(RestartPasswordDTO request)throws GeneralException{
        if(!SqlSafeUtil.isSqlInjectionSafe(request.getNewPassword())){
            logger.debug("Restart new password field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
        if(!SqlSafeUtil.isSqlInjectionSafe(request.getRePassword())){
            logger.debug("Restart repeat password field was not SQL Injection safe, status:400 Bad Request");
            logger.warn("SQL Injection attempt!");
            throw new GeneralException("Nice try!", HttpStatus.BAD_REQUEST);
        }
    }
}
