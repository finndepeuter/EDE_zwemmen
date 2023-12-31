package fact.it.raceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SwimmerResponse {
    private String swimmerCode;
    private String firstName;
    private String lastName;
    private BestTimeResponse[] bestTimes;
}
