package fashionartkids.mgmt.repository;

import fashionartkids.mgmt.entity.TalentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepository extends JpaRepository<TalentEntity, Integer> {
    Page<TalentEntity> findByFirstNameContainingIgnoreCase(String keyword, Pageable pageable);

}
