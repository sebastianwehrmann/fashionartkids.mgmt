package fashionartkids.mgmt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "address")
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String streetNumber;
    private String zip;
    private String city;
    private String country;
    @OneToOne(mappedBy = "address")
    private TalentEntity talent;
}
