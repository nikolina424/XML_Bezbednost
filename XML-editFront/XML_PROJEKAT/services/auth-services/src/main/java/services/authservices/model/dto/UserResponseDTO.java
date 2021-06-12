package services.authservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.authservices.model.UserInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private int id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String token;
    private int tokenExpiresIn;

    public UserResponseDTO(UserInfo userInfo, String token){
        this.id = userInfo.getId();
        this.username = userInfo.getUsername();
        this.name = userInfo.getName();
        this.surname = userInfo.getSurname();
        this.email = userInfo.getEmail();
        this.token = token;
    }
}
