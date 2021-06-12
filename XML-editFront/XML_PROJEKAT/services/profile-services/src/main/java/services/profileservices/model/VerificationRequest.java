package services.profileservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VerificationRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private ProfileCategory category;
    private int officialDocument; //TREBA BITI SLIKA

    public VerificationRequest(String name, String surname, ProfileCategory category, int officialDocument){
        this.name=name;
        this.surname = surname;
        this.category = category;
        this.officialDocument = officialDocument;
    }
}
