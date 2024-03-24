package fashionartkids.mgmt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "media")
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class MediaEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String hash;
    private String type;
    private String filename;
    @ManyToOne
    @JoinColumn(name = "talent_id", nullable = false)
    private TalentEntity talent;

}
