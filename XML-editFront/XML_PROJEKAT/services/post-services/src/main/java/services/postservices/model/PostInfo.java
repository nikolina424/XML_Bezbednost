package services.postservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private String caption;
    @ElementCollection
    @CollectionTable(name="Post_Pictures", joinColumns=@JoinColumn(name="PostInfo_ID"))
    @Column(name="picture")
    private List<String> picture;
    @ElementCollection
    @CollectionTable(name="Post_Videos", joinColumns=@JoinColumn(name="PostInfo_ID"))
    @Column(name="video")
    private List<String> video;
    private int report;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Location location;
    @ElementCollection
    @CollectionTable(name="PostInfo_Tagged", joinColumns=@JoinColumn(name="Post_ID"))
    @Column(name="tag")
    private List<Integer> taggedIds;
}
