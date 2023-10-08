package fact.it.raceservice.service;

import fact.it.raceservice.dto.EventResponse;
import fact.it.raceservice.dto.RaceItemDto;
import fact.it.raceservice.dto.RaceRequest;
import fact.it.raceservice.dto.SwimmerResponse;
import fact.it.raceservice.model.Race;
import fact.it.raceservice.model.RaceItem;
import fact.it.raceservice.repository.RaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RaceService {

    private final RaceRepository raceRepository;
    private final WebClient webClient;

    public boolean registerRace(RaceRequest raceRequest) {
        Race race = new Race();
        race.setName("Placeholder");

        List<RaceItem> raceItems = raceRequest.getRaceItems()
                .stream()
                .map(this::mapToRaceItem)
                .toList();

        race.setRaceItemList(raceItems);

        List<String> eventCodes = race.getRaceItemList().stream()
                .map(RaceItem::getEventCode)
                .toList();

        List<String> swimmerCodes = race.getRaceItemList().stream()
                .map(RaceItem::getSwimmerCode)
                .toList();

        EventResponse[] eventResponses = webClient.get()
                .uri("http://localhost:8082/api/event",
                        uriBuilder -> uriBuilder.queryParam("eventCode", eventCodes).build())
                .retrieve()
                .bodyToMono(EventResponse[].class)
                .block();

        boolean isAvailable = Arrays.stream(eventResponses)
                .allMatch(EventResponse::isAvailable);

        if(isAvailable) {
            SwimmerResponse[] swimmerResponses = webClient.get()
                    .uri("http://localhost:8080/api/swimmer",
                            uriBuilder -> uriBuilder.queryParam("swimmerCode", swimmerCodes).build())
                    .retrieve()
                    .bodyToMono(SwimmerResponse[].class)
                    .block();

            race.getRaceItemList().stream()
                            .map(raceItem -> {
                                SwimmerResponse swimmer = Arrays.stream(swimmerResponses)
                                        .filter(s -> s.getSwimmerCode().equals(raceItem.getSwimmerCode()))
                                        .findFirst()
                                        .orElse(null);
                                if (swimmer != null) {
                                    raceItem.setSwimmerCode(swimmer.getSwimmerCode());
                                }
                                return raceItem;
                            })
                                    .collect(Collectors.toList());
            raceRepository.save(race);
            return true;
        } else {
            return false;
        }
    }

    private RaceItem mapToRaceItem(RaceItemDto raceItemDto) {
        RaceItem raceItem = new RaceItem();
        raceItem.setBestTime(raceItemDto.getBestTime());
        raceItem.setEventCode(raceItemDto.getEventCode());
        raceItem.setSwimmerCode(raceItemDto.getSwimmerCode());
        return raceItem;
    }

}
