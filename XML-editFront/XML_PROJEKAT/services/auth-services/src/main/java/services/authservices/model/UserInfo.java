package services.authservices.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import services.authservices.model.dto.RegistrationDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

@Entity(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   // @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate dateOfBirth;

    public UserInfo(RegistrationDTO registrationDTO){
        this.username = registrationDTO.getUsername();
        this.password = registrationDTO.getPassword();
        this.name = registrationDTO.getName();
        this.surname = registrationDTO.getSurname();
        this.email = registrationDTO.getEmail();
        this.phone = registrationDTO.getPhone();
        if(registrationDTO.getGender().toLowerCase().equals(Gender.Male.toString().toLowerCase(Locale.ROOT)))
            this.gender = Gender.Male;
        else if(registrationDTO.getGender().toLowerCase().equals(Gender.Female.toString().toLowerCase(Locale.ROOT)))
            this.gender = Gender.Female;
        else
            this.gender = Gender.NonBinary;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;
}
