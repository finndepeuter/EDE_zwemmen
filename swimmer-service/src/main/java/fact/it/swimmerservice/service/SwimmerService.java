package fact.it.swimmerservice.service;

import fact.it.swimmerservice.dto.SwimmerRequest;
import fact.it.swimmerservice.dto.SwimmerResponse;
import fact.it.swimmerservice.model.BestTime;
import fact.it.swimmerservice.model.Swimmer;
import fact.it.swimmerservice.repository.SwimmerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SwimmerService {

    private final SwimmerRepository swimmerRepository;

    @PostConstruct
    public void loadData() {
        swimmerRepository.deleteAll();
        if (swimmerRepository.count() <=0) {
            List<BestTime> bestTimes = new ArrayList<>();
            BestTime bestTime1 = BestTime.builder()
                    .time("29.89")
                    .eventCode("50free")
                    .build();
            BestTime bestTime2 = BestTime.builder()
                    .time("34.20")
                    .eventCode("50back")
                    .build();
            bestTimes.add(bestTime1);
            bestTimes.add(bestTime2);

            Swimmer swimmer = Swimmer.builder()
                    .id("1")
                    .swimmerCode("bobjansens2001")
                    .firstName("Bob")
                    .lastName("Jansens")
                    .club("Moska")
                    .birthYear(2001)
                    .bestTimes(bestTimes).build();

            Swimmer swimmer1 = Swimmer.builder()
                    .id("2")
                    .swimmerCode("finndepeuter2001")
                    .firstName("Finn")
                    .lastName("De Peuter")
                    .club("HZA")
                    .birthYear(2001)
                    .bestTimes(bestTimes).build();

            swimmerRepository.save(swimmer);
            swimmerRepository.save(swimmer1);
        }
    }

    public void createSwimmer(SwimmerRequest swimmerRequest){
        Swimmer swimmer = Swimmer.builder()
                .swimmerCode(swimmerRequest.getSwimmerCode())
                .firstName(swimmerRequest.getFirstName())
                .lastName(swimmerRequest.getLastName())
                .club(swimmerRequest.getClub())
                .birthYear(swimmerRequest.getBirthYear())
                .build();

        swimmerRepository.save(swimmer);
    }

    public List<SwimmerResponse> getAllSwimmers() {
        List<Swimmer> swimmers = swimmerRepository.findAll();

        return swimmers.stream().map(this::mapToSwimmerResponse).toList();
    }

    public List<SwimmerResponse> getSwimmerBySwimmerCode(String swimmerCode) {
        List<Swimmer> swimmers = swimmerRepository.findSwimmerBySwimmerCode(swimmerCode);

        return swimmers.stream().map(this::mapToSwimmerResponse).toList();
    }


    private SwimmerResponse mapToSwimmerResponse(Swimmer swimmer) {
        return SwimmerResponse.builder()
                .swimmerCode(swimmer.getSwimmerCode())
                .firstName(swimmer.getFirstName())
                .lastName(swimmer.getLastName())
                .club(swimmer.getClub())
                .bestTimes(swimmer.getBestTimes())
                .build();
    }

    public boolean updateSwimmer(SwimmerRequest swimmerRequest) {
        String swimmerCode = swimmerRequest.getSwimmerCode();
        Optional<Swimmer> swimmer = swimmerRepository.findSwimmerBySwimmerCode(swimmerCode).stream().findFirst();
        if (swimmer.isPresent()) {
            Swimmer swimmer1 = swimmer.get();
            swimmer1.setLastName(swimmerRequest.getLastName());
            swimmer1.setFirstName(swimmerRequest.getFirstName());
            swimmer1.setClub(swimmerRequest.getClub());
            swimmer1.setBirthYear(swimmerRequest.getBirthYear());

            swimmerRepository.save(swimmer1);
            return true;
        }
        return false;
    }
}
