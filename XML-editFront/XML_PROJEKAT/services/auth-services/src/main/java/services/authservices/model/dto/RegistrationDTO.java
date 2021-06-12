package services.authservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    String username;
    String name;
    String surname;
    String email;
    String password;
    String phone;
    String birthday;
    String gender;
}
