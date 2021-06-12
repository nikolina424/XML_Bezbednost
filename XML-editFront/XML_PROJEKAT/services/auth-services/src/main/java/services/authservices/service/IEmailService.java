package services.authservices.service;

import services.authservices.model.UserInfo;

public interface IEmailService {
    void restartPassword(UserInfo user);
}
