package fact.it.swimmerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "swimmer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Swimmer {
    private String id;
    private String firstName;
    private String lastName;
    private String club;
    private String swimmerCode;
    private Integer birthYear;
    private BestTime bestTimes;

}
