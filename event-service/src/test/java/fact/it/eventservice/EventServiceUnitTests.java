package fact.it.eventservice;

import fact.it.eventservice.dto.EventResponse;
import fact.it.eventservice.model.Event;
import fact.it.eventservice.repository.EventRepository;
import fact.it.eventservice.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class EventServiceUnitTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Test
    void testGetEvents() {
        // Arrange
        List<Event> mockEvents = Arrays.asList(
                new Event(1L, "50m Freestyle", "50free",24),
                new Event(2L, "50m Fly", "50fly", 2),
                new Event(3L,"50m backstroke", "50back", 10),
                new Event(4L,"50m breaststroke", "50breast", 0)
        );
        when(eventRepository.findAll()).thenReturn(mockEvents);

        // Act
        List<EventResponse> result = eventService.getEvents();

        // Assert
        assertEquals(mockEvents.size(), result.size());
        // Add more assertions based on your specific requirements
    }

    @Test
    void testGetEventByEventCode() {
        // Arrange
        String eventCode = "50free";
        List<Event> mockEvents = Arrays.asList(new Event(1L, "50m Freestyle", eventCode, 24));
        when(eventRepository.findEventsByEventCode(eventCode)).thenReturn(mockEvents);

        // Act
        List<EventResponse> result = eventService.getEventByEventCode(eventCode);

        // Assert
        assertEquals(mockEvents.size(), result.size());
        assertEquals("50free", result.get(0).getEventCode());
        // Add more assertions based on your specific requirements
    }

    @Test
    void testGetEventsAvailable() {
        // Arrange
        List<Event> mockEvents = Arrays.asList(
                new Event(1L, "50m Freestyle", "50free", 24),
                new Event(2L, "50m backstroke", "50back", 10),
                new Event(3L, "50 Breaststroke", "50breast", 0)
        );
        when(eventRepository.findEventsByFreeSpotsGreaterThan(0)).thenReturn(mockEvents);

        // Act
        List<EventResponse> eventResponses = eventService.getEventsAvailable();

        // Assert
        assertEquals(mockEvents.size(), eventResponses.size());
        assertEquals("50free", eventResponses.get(0).getEventCode());
        assertTrue(eventResponses.get(0).isAvailable());
        assertEquals("50back", eventResponses.get(1).getEventCode());
        assertTrue(eventResponses.get(1).isAvailable());
        assertEquals("50breast", eventResponses.get(2).getEventCode());
        assertFalse(eventResponses.get(2).isAvailable());

        verify(eventRepository, times(1)).findEventsByFreeSpotsGreaterThan(0);
        // Add more assertions based on your specific requirements
    }

    // Add more tests as needed for other methods in the EventService class
}