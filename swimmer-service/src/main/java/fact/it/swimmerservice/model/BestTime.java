package fact.it.swimmerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "besttime")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BestTime {
    private String eventCode;
    private String time;
}
