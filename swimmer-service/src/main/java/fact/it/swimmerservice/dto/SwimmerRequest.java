package fact.it.swimmerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SwimmerRequest {
    private String swimmerCode;
    private String firstName;
    private String lastName;
    private int birthYear;
    private String club;
}
