package services.authservices.service.implementation;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import services.authservices.config.EmailContext;
import services.authservices.model.UserInfo;
import services.authservices.repository.AuthRepository;
import services.authservices.service.IEmailService;

@Service
public class EmailService implements IEmailService {

    private final EmailContext emailContext;
    private final AuthRepository authRepository;


    public EmailService(EmailContext emailContext, AuthRepository authRepository) {
        this.emailContext = emailContext;
        this.authRepository = authRepository;
    }

    @Override
    public void restartPassword(UserInfo user) {
        String to = user.getEmail();
        String subject = "Restart password";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", user.getName(), user.getSurname()));
        context.setVariable("link", String.format("http://localhost:4200/reset-password/%s", user.getId()));
        emailContext.send(to, subject, "resetPassword", context);
    }
}
