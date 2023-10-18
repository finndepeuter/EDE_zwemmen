package fact.it.raceservice.repository;

import fact.it.raceservice.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceRepository extends JpaRepository<Race, Long> {
    Race findRaceByRaceId(String raceId);
}
