package fact.it.swimmerservice.controller;

import fact.it.swimmerservice.dto.SwimmerRequest;
import fact.it.swimmerservice.dto.SwimmerResponse;
import fact.it.swimmerservice.model.Swimmer;
import fact.it.swimmerservice.service.SwimmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/swimmer")
@RequiredArgsConstructor
public class SwimmerController {
    private final SwimmerService swimmerService;

    // GET all swimmers
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SwimmerResponse> getAllSwimmers() {return swimmerService.getAllSwimmers();}

    // GET swimmer by swimmercode
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SwimmerResponse> getSwimmerBySwimmerCode(
            @RequestParam String swimmerCode) {
        return swimmerService.getSwimmerBySwimmerCode(swimmerCode);
    }

    // POST create swimmer
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createSwimmer(@RequestBody SwimmerRequest swimmerRequest) {
        swimmerService.createSwimmer(swimmerRequest);
    }

    // TODO put swimmer
    // PUT swimmer
//    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Swimmer> updateSwimmer(@RequestBody SwimmerRequest swimmerRequest, @PathVariable("swimmerCode") String swimmerCode) {
//        Optional<Swimmer> swimmer = swimmerService.updateSwimmerBySwimmercode(swimmerCode)
//    }
}
