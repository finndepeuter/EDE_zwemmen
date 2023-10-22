package fact.it.raceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceRequest {
    private String name;
    private Date date;
    private String swimmerCode;
    private String eventCode;
}
