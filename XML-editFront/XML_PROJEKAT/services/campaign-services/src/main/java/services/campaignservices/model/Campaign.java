package services.campaignservices.model;

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
public class Campaign {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate startDate;
    private LocalDate changedDate; //zbog provere, da li moze da se promeni kampanja(tek za 24h sme!)
    private LocalDate endDate;
    private int countAllowed; //koliko puta treba plasirati
    private int countDone; // koliko puta je plasirana
    //private List<Integer> postIds;
    //private List<Integer> storyIds;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Campaign_ID")
    private List<Commercial> commercials;
}
