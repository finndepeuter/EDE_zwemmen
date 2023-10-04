package fact.it.raceservice.service;

import fact.it.raceservice.dto.EventResponse;
import fact.it.raceservice.dto.RaceRequest;
import fact.it.raceservice.model.Race;
import fact.it.raceservice.repository.RaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
public class RaceService {

    private final RaceRepository raceRepository;
    private final WebClient webClient;

    public boolean registerRace(RaceRequest raceRequest) {
        Race race = new Race();
        race.setName("Placeholder");

        String eventCode = race.getEventCode();

        EventResponse[] eventResponses = webClient.get()
                .uri("http://localhost:8082/api/event",
                        uriBuilder -> uriBuilder.queryParam("eventCode", eventCode).build())
                .retrieve()
                .bodyToMono(EventResponse[].class)
                .block();

        boolean isAvailable = Arrays.stream(eventResponses)
                .allMatch(EventResponse::isAvailable);

    }

}
