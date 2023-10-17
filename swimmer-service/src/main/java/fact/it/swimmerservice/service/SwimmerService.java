package fact.it.swimmerservice.service;

import fact.it.swimmerservice.dto.SwimmerRequest;
import fact.it.swimmerservice.dto.SwimmerResponse;
import fact.it.swimmerservice.model.BestTime;
import fact.it.swimmerservice.model.Swimmer;
import fact.it.swimmerservice.repository.SwimmerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SwimmerService {

    private final SwimmerRepository swimmerRepository;


    @PostConstruct
    public void loadData() {
        if (swimmerRepository.count() <=0) {
            BestTime bestTime;
            Swimmer swimmer = Swimmer.builder()
                    .id("1")
                    .swimmerCode("bobjansens2001")
                    .firstName("Bob")
                    .lastName("Jansen")
                    .club("HZA")
                    .birthYear(2001)
                    .bestTimes(bestTime = BestTime.builder()
                            .time("34.20")
                            .eventCode("50free")
                            .build()).build();

            swimmerRepository.save(swimmer);
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

    public List<SwimmerResponse> getSwimmerBySwimmerCode(List<String> swimmerCode) {
        List<Swimmer> swimmers = swimmerRepository.findBySwimmerCodeIn(swimmerCode);

        return swimmers.stream().map(this::mapToSwimmerResponse).toList();
    }


//    public Optional<Swimmer> getOptionalSwimmerByCode(String swimmerCode){
//        return getAllSwimmers().stream().filter(s-> s.getSwimmerCode()==swimmerCode).findFirst();
//    }
//
//    public Swimmer updateSwimmerBySwimmerCode(Swimmer updateSwimmer, List<String> swimmerCode) {
//        List<SwimmerResponse> swimmerOptional = getSwimmerBySwimmerCode(swimmerCode) ;
//        if(swimmerOptional.isEmpty()) {
//            return null;
//        } else {
//            Swimmer swimmer = swimmerOptional.get();
//        }
//    }

    private SwimmerResponse mapToSwimmerResponse(Swimmer swimmer) {
        return SwimmerResponse.builder()
                .id(swimmer.getId())
                .swimmerCode(swimmer.getSwimmerCode())
                .firstName(swimmer.getFirstName())
                .lastName(swimmer.getLastName())
                .club(swimmer.getClub())
                .birthYear(swimmer.getBirthYear())
                .bestTimes(swimmer.getBestTimes())
                .build();
    }
}
