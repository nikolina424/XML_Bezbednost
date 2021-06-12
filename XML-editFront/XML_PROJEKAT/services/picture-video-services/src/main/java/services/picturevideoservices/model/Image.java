package services.picturevideoservices.model;

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
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    @Lob
    private byte[] content;
    private String name;
    private String location;

    public Image(String imageName, String location) {
        this.name = imageName;
        this.location = location;
    }
}
