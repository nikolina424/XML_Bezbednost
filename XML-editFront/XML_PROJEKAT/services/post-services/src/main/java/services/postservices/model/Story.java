package services.postservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Story {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean closeFriends;
    private Boolean highlight;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PostInfo postInfo;
}
