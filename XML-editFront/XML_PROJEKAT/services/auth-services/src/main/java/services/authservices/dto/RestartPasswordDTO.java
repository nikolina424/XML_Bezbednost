package services.authservices.dto;

import lombok.Data;

@Data
public class RestartPasswordDTO {
    private String newPassword;
    private String rePassword;
}
