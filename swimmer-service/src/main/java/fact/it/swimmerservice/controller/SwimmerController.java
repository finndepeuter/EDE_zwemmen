package fact.it.swimmerservice.controller;

import fact.it.swimmerservice.dto.SwimmerRequest;
import fact.it.swimmerservice.dto.SwimmerResponse;
import fact.it.swimmerservice.service.SwimmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/swimmer")
@RequiredArgsConstructor
public class SwimmerController {
    private final SwimmerService swimmerService;

    // GET all swimmers
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SwimmerResponse> getAllSwimmers() {return swimmerService.getAllSwimmers();}

    // GET swimmer by swimmercode
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SwimmerResponse> getAllSwimmersBySwimmerCode(
            @RequestParam List<String> swimmerCode) {
        return swimmerService.getAllSwimmersBySwimmerCode(swimmerCode);
    }

    // POST create swimmer
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createSwimmer(@RequestBody SwimmerRequest swimmerRequest) {
        swimmerService.createSwimmer(swimmerRequest);
    }

    // PUT swimmer, change the club and besttimes of the swimmer
}
