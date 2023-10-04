package com.example.swimmerservice.service;

import com.example.swimmerservice.dto.SwimmerRequest;
import com.example.swimmerservice.dto.SwimmerResponse;
import com.example.swimmerservice.model.Swimmer;
import com.example.swimmerservice.repository.SwimmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwimmerService {

    private final SwimmerRepository swimmerRepository;

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

    public List<SwimmerResponse> getAllSwimmersBySwimmerCode(List<String> swimmerCode) {
        List<Swimmer> swimmers = swimmerRepository.findBySwimmerCodeIn(swimmerCode);

        return swimmers.stream().map(this::mapToSwimmerResponse).toList();
    }

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
