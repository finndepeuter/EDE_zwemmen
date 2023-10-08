package fact.it.raceservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raceitem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String swimmerCode;
    private String eventCode;
    private String bestTime;
}
