package prototype.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prototype.backend.model.Event;

import java.util.Date;
import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByDateBetween(Date start, Date end);

    @Query("SELECT e FROM Event e WHERE YEAR(e.date) = :year AND MONTH(e.date) = :month")
    List<Event> findByDateInMonthAndYear(int month, int year);
}
