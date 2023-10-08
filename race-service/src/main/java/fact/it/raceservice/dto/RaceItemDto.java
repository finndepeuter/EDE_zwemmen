package fact.it.raceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceItemDto {
    private Long id;
    private String eventCode;
    private String swimmerCode;
    private String bestTime;
}
