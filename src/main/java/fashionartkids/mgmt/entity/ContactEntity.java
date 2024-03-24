package fashionartkids.mgmt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "contact")
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String phone;
    private String mobile;
    private String email;
    @OneToOne(mappedBy = "contact")
    private TalentEntity talent;
}
