package com.example.swimmerservice.controller;

import com.example.swimmerservice.dto.SwimmerRequest;
import com.example.swimmerservice.dto.SwimmerResponse;
import com.example.swimmerservice.service.SwimmerService;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SwimmerResponse> getAllSwimmersBySwimmerCode(
            @RequestParam List<String> swimmerCode) {
        return swimmerService.getAllSwimmersBySwimmerCode(swimmerCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createSwimmer(@RequestBody SwimmerRequest swimmerRequest) {
        swimmerService.createSwimmer(swimmerRequest);
    }
}
