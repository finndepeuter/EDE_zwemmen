package fact.it.eventservice.repository;

import fact.it.eventservice.model.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventCodeIn(List<String> eventCode);
}
