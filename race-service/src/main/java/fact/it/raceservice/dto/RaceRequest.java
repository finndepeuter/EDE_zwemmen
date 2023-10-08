package fact.it.raceservice.dto;

import fact.it.raceservice.model.RaceItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceRequest {
    private List<RaceItemDto> raceItems;
}
