package fashionartkids.mgmt.entity;

import fashionartkids.mgmt.model.job.Job;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "talent")
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class TalentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private ContactEntity contact;
    private Integer height;
    private Integer weight;
    private Integer clothingSize;
    private Integer shoeSize;
    private String eyeColor;
    private String hairColor;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime lastUpdated;
    @OneToMany(mappedBy = "talent")
    private List<MediaEntity> media;
    @OneToMany(mappedBy = "talent")
    private List<Job> jobs;
}
