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
        if(eventRepository.count() <= 0) {
            Event event = new Event();
            event.setEventCode("50free");
            event.setName("50 Freestyle");
            event.setParticipants(24);

            Event event1 = new Event();
            event1.setEventCode("50fly");
            event1.setName("50 Fly");
            event1.setParticipants(2);

            Event event2 = new Event();
            event2.setEventCode("50back");
            event2.setName("50 backstroke");
            event2.setParticipants(10);

            eventRepository.save(event);
            eventRepository.save(event1);
            eventRepository.save(event2);
        }
    }

    @Transactional(readOnly = true)
    public List<EventResponse> isAvailable(List<String> eventCode) {
        return eventRepository.findByEventCodeIn(eventCode).stream()
                .map(event ->
                        EventResponse.builder()
                                .eventCode(event.getEventCode())
                                .participants(event.getParticipants())
                                .isAvailable(event.getParticipants() > 0)
                                .build()
                ).toList();
    }
}
