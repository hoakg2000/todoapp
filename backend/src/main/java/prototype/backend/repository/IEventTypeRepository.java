package prototype.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prototype.backend.model.EventType;

@Repository
public interface IEventTypeRepository extends JpaRepository<EventType, Integer> {
}
