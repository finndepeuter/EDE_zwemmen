package fact.it.raceservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "race")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String raceId;
    private String name;
    private Date date;

    private String eventCode;
    private String eventName;

    private String swimmerFirstName;
    private String swimmerLastName;
    private String bestTimeForEvent;

}
