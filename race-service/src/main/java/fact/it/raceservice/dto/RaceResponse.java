package fact.it.raceservice.dto;

import fact.it.raceservice.model.Race;
import fact.it.raceservice.model.RaceItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceResponse {
    private String name;
    private List<RaceItem> raceItems;
}
