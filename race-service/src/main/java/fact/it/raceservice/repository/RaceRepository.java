package fact.it.raceservice.repository;

import fact.it.raceservice.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
}
