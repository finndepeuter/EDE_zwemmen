package fact.it.raceservice.service;

import fact.it.raceservice.dto.*;
import fact.it.raceservice.model.Race;
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

    public List<RaceResponse> getAllRaces() {
        List<Race> races = raceRepository.findAll();
        return races.stream().map(this::mapToRaceResponse).toList();
    }

    // TODO figure out registerRace
    public boolean registerRace(RaceRequest raceRequest) {
        return false;
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
