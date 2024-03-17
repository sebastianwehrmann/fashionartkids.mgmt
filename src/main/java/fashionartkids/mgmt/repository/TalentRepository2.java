package fashionartkids.mgmt.repository;

import fashionartkids.mgmt.entity.TalentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepository2 extends DataTablesRepository<TalentEntity, Integer> {

}
