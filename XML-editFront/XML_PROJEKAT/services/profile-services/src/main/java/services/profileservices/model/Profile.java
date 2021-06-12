package services.profileservices.model;


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
public class Profile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String biography;
    private Boolean isPrivate;
    private String website;
    private ProfileCategory category;
    private Boolean canBeTagged;
    private Boolean canBeMessaged;
    @ElementCollection
    @CollectionTable(name="Profile_Posts", joinColumns=@JoinColumn(name="Profile_ID"))
    @Column(name="post")
    private List<Integer> postIds;
    @ElementCollection
    @CollectionTable(name="Profile_Stories", joinColumns=@JoinColumn(name="Profile_ID"))
    @Column(name="story")
    private List<Integer> storyIds;
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private int userInfoId;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Profile_Following",
            joinColumns = { @JoinColumn(name = "Profile_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Following_ID") })
    private List<Profile> following;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Profile_Followers",
            joinColumns = { @JoinColumn(name = "Profile_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Followers_ID") })
    private List<Profile> followers;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Profile_Friends",
            joinColumns = { @JoinColumn(name = "Profile_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Friends_ID") })
    private List<Profile> friends;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Profile_Blocked",
            joinColumns = { @JoinColumn(name = "Profile_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Blocked_ID") })
    private List<Profile> blocked;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Profile_Muted",
            joinColumns = { @JoinColumn(name = "Profile_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Muted_ID") })
    private List<Profile> muted;

}
