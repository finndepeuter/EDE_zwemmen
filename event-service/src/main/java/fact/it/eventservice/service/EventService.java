package fact.it.eventservice.service;

import fact.it.eventservice.dto.EventResponse;
import fact.it.eventservice.model.Event;
import fact.it.eventservice.repository.EventRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @PostConstruct
    public void loadData() {
        eventRepository.deleteAll();
        if(eventRepository.count() <= 0) {
            Event event = new Event();
            event.setEventCode("50free");
            event.setName("50m Freestyle");
            event.setFreeSpots(24);

            Event event1 = new Event();
            event1.setEventCode("50fly");
            event1.setName("50m Fly");
            event1.setFreeSpots(2);

            Event event2 = new Event();
            event2.setEventCode("50back");
            event2.setName("50m Backstroke");
            event2.setFreeSpots(10);

            Event event3 = new Event();
            event3.setEventCode("50breast");
            event3.setName("50m Breaststroke");
            event3.setFreeSpots(0);

            Event event4 = new Event();
            event4.setEventCode("100IM");
            event4.setName("100m Individual Medley");
            event4.setFreeSpots(10);

            Event event5 = new Event();
            event5.setEventCode("100free");
            event5.setName("100m Freestyle");
            event5.setFreeSpots(2);

            eventRepository.save(event);
            eventRepository.save(event1);
            eventRepository.save(event2);
            eventRepository.save(event3);
            eventRepository.save(event4);
            eventRepository.save(event5);
        }
    }

    public List<EventResponse> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::mapToEventResponse).toList();
    }

    public List<EventResponse> getEventByEventCode(String eventCode) {
        List<Event> events = eventRepository.findEventsByEventCode(eventCode);
        return events.stream().map(this::mapToEventResponse).toList();
    }

    public List<EventResponse> getEventsAvailable() {
        List<Event> events = eventRepository.findEventsByFreeSpotsGreaterThan(0);
        return events.stream().map(this::mapToEventResponse).toList();
    }

    private EventResponse mapToEventResponse(Event event) {
        return EventResponse.builder()
                .eventCode(event.getEventCode())
                .name(event.getName())
                .isAvailable(event.getFreeSpots() > 0)
                .build();

    }


}
