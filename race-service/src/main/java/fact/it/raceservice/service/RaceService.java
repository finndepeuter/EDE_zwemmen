package fact.it.raceservice.service;

import fact.it.raceservice.dto.*;
import fact.it.raceservice.model.Race;
import fact.it.raceservice.repository.RaceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RaceService {

    private final RaceRepository raceRepository;
    private final WebClient webClient;

    @PostConstruct
    public void loadData() {
        raceRepository.deleteAll();
        if (raceRepository.count() <= 0) {
            Race race = new Race();
            race.setName("Clubkampioenschap");
            race.setRaceId("1");
            race.setEventCode("50free");
            race.setEventName("50m freestyle");
            race.setDate(new Date());
            race.setSwimmerFirstName("JOst");
            race.setSwimmerLastName("peeters");
            race.setBestTimeForEvent("35.20");

            raceRepository.save(race);
        }

    }

    public List<RaceResponse> getAllRaces() {
        List<Race> races = raceRepository.findAll();
        return races.stream().map(this::mapToRaceResponse).toList();
    }

    public RaceResponse getRaceByRaceId(String raceId) {
        Race race = raceRepository.findRaceByRaceId(raceId);
        return mapToRaceResponse(race);
    }


    public boolean registerRace(RaceRequest raceRequest) {
        Race race = new Race();
        race.setRaceId(UUID.randomUUID().toString());
        race.setName(raceRequest.getName());
        race.setDate(raceRequest.getDate());

        String eventCode = raceRequest.getEventCode();

        EventResponse[] eventAr = webClient.get()
                .uri("http://localhost:8082/api/event", uriBuilder -> uriBuilder.queryParam("eventCode", eventCode).build())
                .retrieve()
                .bodyToMono(EventResponse[].class)
                .block();

        String swimmerCode = raceRequest.getSwimmerCode();

        SwimmerResponse[] swimmerAr = webClient.get()
                .uri("http://localhost:8081/api/swimmer", uriBuilder -> uriBuilder.queryParam("swimmerCode", swimmerCode).build())
                .retrieve()
                .bodyToMono(SwimmerResponse[].class)
                .block();

        boolean isAvailable = Arrays.stream(eventAr).allMatch(EventResponse::isAvailable);

        if (isAvailable){
            EventResponse event = Arrays.stream(eventAr)
                    .filter(e -> e.getEventCode().equals(raceRequest.getEventCode()))
                    .findFirst()
                    .orElse(null);
            if (event != null) {
                race.setEventName(event.getName());
            }
            SwimmerResponse swimmer = Arrays.stream(swimmerAr)
                    .filter(s -> s.getSwimmerCode().equals(raceRequest.getSwimmerCode()))
                    .findFirst()
                    .orElse(null);
            if (swimmer != null) {
                race.setSwimmerFirstName(swimmer.getFirstName());
                race.setSwimmerLastName(swimmer.getLastName());

                BestTimeResponse[] bestTimes = swimmer.getBestTimes();
                BestTimeResponse bestTimeForEvent = Arrays.stream(bestTimes)
                                .filter(t -> t.getEventCode().equals(raceRequest.getEventCode()))
                                .findFirst()
                                .orElse(null);
                if (bestTimeForEvent != null) {
                    race.setBestTimeForEvent(bestTimeForEvent.getTime());
                }
            }

            raceRepository.save(race);
            return true;

        } else {

            return false;
        }
    }

    public boolean deleteRace(String raceId) {
        Race race = raceRepository.findRaceByRaceId(raceId);
        if(race != null) {
            raceRepository.deleteById(race.getId());
            return true;
        } else {
            return false;
        }
    }


    private RaceResponse mapToRaceResponse(Race race) {
        return RaceResponse.builder()
                .name(race.getName())
                .raceId(race.getRaceId())
                .date(race.getDate())
                .eventName(race.getEventName())
                .swimmer(race.getSwimmerFirstName() + ' ' + race.getSwimmerLastName())
                .bestTime(race.getBestTimeForEvent())
                .build();
    }
}
