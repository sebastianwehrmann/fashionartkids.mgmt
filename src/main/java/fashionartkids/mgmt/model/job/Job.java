package fashionartkids.mgmt.model.job;

import fashionartkids.mgmt.entity.TalentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String customer;
    private String start;
    private String end;
    private Float rate;
    @ManyToOne
    @JoinColumn(name = "talent_id")
    private TalentEntity talent;

}
