package fact.it.swimmerservice.repository;

import fact.it.swimmerservice.model.Swimmer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SwimmerRepository extends MongoRepository<Swimmer, String> {
    List<Swimmer> findSwimmerBySwimmerCode(String swimmerCode);
}
