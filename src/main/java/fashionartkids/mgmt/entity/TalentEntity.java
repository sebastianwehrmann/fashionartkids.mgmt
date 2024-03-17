package fashionartkids.mgmt.entity;

import fashionartkids.mgmt.model.job.Job;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "talent")
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class TalentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String street;
    private String streetNumber;
    private String zip;
    private String city;
    private String country;
    private String phone;
    private String mobile;
    private String email;
    private Integer height;
    private Integer weight;
    private Integer clothingSize;
    private Integer shoeSize;
    private String eyeColor;
    @OneToMany(mappedBy = "talent")
    private List<Image> images;
    @OneToMany(mappedBy = "talent")
    private List<Job> jobs;
}
