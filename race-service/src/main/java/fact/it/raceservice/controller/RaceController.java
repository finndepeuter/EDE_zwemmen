package fact.it.raceservice.controller;

import fact.it.raceservice.dto.RaceRequest;
import fact.it.raceservice.dto.RaceResponse;
import fact.it.raceservice.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race")
@RequiredArgsConstructor
public class RaceController {
    private final RaceService raceService;

    // GET all races
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RaceResponse> getRaces() {
        return raceService.getAllRaces();
    }
    // POST race
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String registerRace(@RequestBody RaceRequest raceRequest) {
        boolean result = raceService.registerRace(raceRequest);
        return (result ? "Race successfully registered" : "Race registering failed");
    }
}
