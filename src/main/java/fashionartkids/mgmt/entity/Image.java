package fashionartkids.mgmt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hash;
    @ManyToOne
    @JoinColumn(name = "talent_id", nullable = false)
    private TalentEntity talent;
}
