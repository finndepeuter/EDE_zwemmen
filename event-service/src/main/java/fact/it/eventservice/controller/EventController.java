package fact.it.eventservice.controller;

import fact.it.eventservice.dto.EventResponse;
import fact.it.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    // GET all events
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getEvents() {
        return eventService.getEvents();
    }

    // GET eventby eventcode
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getEventByEventCode(@RequestParam List<String> eventcode) {
        return eventService.getEventByEventCode(eventcode);
    }


}
