package fashionartkids.mgmt.repository;

import fashionartkids.mgmt.entity.TalentEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepository extends DataTablesRepository<TalentEntity, Integer> {

}
