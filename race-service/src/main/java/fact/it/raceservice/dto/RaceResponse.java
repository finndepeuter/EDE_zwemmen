package fact.it.raceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceResponse {
    private String name;
    private String raceId;
    private Date date;
    private String eventName;
    private String swimmer;
    private String bestTime;
}
