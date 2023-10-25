package fact.it.swimmerservice.dto;

import fact.it.swimmerservice.model.BestTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SwimmerResponse {
    private String swimmerCode;
    private String firstName;
    private String lastName;
    private String club;
    private List<BestTime> bestTimes;
}
