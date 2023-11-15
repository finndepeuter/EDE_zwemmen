package fact.it.raceservice;

import fact.it.raceservice.dto.*;
import fact.it.raceservice.model.Race;
import fact.it.raceservice.repository.RaceRepository;
import fact.it.raceservice.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RaceServiceUnitTests {

	@Mock
	private RaceRepository raceRepository;

	@Mock
	private WebClient webClient;

	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

	@Mock
	private WebClient.RequestHeadersSpec requestHeadersSpec;

	@Mock
	private WebClient.ResponseSpec responseSpec;

	@InjectMocks
	private RaceService raceService;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(raceService, "eventServiceBaseUrl", "http://localhost:8082");
		ReflectionTestUtils.setField(raceService, "swimmerServiceBaseUrl", "http://localhost:8081");
	}

	@Test
	void testGetAllRaces() {
		// Arrange
		List<Race> mockRaces = Arrays.asList(new Race());
		when(raceRepository.findAll()).thenReturn(mockRaces);

		// Act
		List<RaceResponse> result = raceService.getAllRaces();

		// Assert
		assertEquals(mockRaces.size(), result.size());
		// Add more assertions based on your specific requirements
	}

	@Test
	void testGetRaceByRaceId() {
		// Arrange
		String raceId = "1";
		Race mockRace = new Race();
		when(raceRepository.findRaceByRaceId(raceId)).thenReturn(mockRace);

		// Act
		RaceResponse result = raceService.getRaceByRaceId(raceId);

		// Assert
		assertNotNull(result);
		// Add more assertions based on your specific requirements
	}

	@Test
	void testRegisterRaceSuccess() {
		// Arrange
		String name = "Clubkampioenschap";
		Date date = new Date();
		String swimmerCode = "finndepeuter2001";
		String eventCode = "50free";

		RaceRequest raceRequest = new RaceRequest();
		raceRequest.setDate(date);
		raceRequest.setName(name);
		raceRequest.setEventCode(eventCode);
		raceRequest.setSwimmerCode(swimmerCode);

		// Mock responses from event and swimmer services
		EventResponse eventResponse = new EventResponse();
		eventResponse.setEventCode(eventCode);
		eventResponse.setAvailable(true);
		eventResponse.setName("50m freestyle");

		BestTimeResponse[] bestTimeResponse = {new BestTimeResponse(eventCode, "39.20")};


		SwimmerResponse swimmerResponse = new SwimmerResponse();
		swimmerResponse.setSwimmerCode(swimmerCode);
		swimmerResponse.setFirstName("Finn");
		swimmerResponse.setLastName("De Peuter");
		swimmerResponse.setBestTimes(bestTimeResponse);


		Race race = new Race();
		race.setId(1L);
		race.setRaceId("1");
		race.setName(name);
		race.setDate(date);

		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(anyString(), any(Function.class))).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EventResponse[].class)).thenReturn(Mono.just(new EventResponse[]{eventResponse}));
		when(responseSpec.bodyToMono(SwimmerResponse[].class)).thenReturn(Mono.just(new SwimmerResponse[]{swimmerResponse}));
		//when(responseSpec.bodyToMono(BestTimeResponse[].class)).thenReturn(Mono.just(bestTimeResponse));
		when(raceRepository.save(any(Race.class))).thenReturn(new Race());
		// Act
		boolean result = raceService.registerRace(raceRequest);

		// Assert
		assertTrue(result);

		verify(raceRepository, times(1)).save(any(Race.class));
		// Add more assertions based on your specific requirements
	}

	@Test
	void testRegisterRaceFailure() {
		// Arrange
		String name = "Clubkampioenschap";
		Date date = new Date();
		String swimmerCode = "finndepeuter2001";
		String eventCode = "50free";

		RaceRequest raceRequest = new RaceRequest();
		raceRequest.setDate(date);
		raceRequest.setName(name);
		raceRequest.setEventCode(eventCode);
		raceRequest.setSwimmerCode(swimmerCode);

		// Mock responses from event and swimmer services
		EventResponse eventResponse = new EventResponse();
		eventResponse.setEventCode(eventCode);
		eventResponse.setAvailable(false);
		eventResponse.setName("50m freestyle");

		BestTimeResponse[] bestTimeResponse = {new BestTimeResponse(eventCode, "39.20")};

		SwimmerResponse swimmerResponse = new SwimmerResponse();
		swimmerResponse.setSwimmerCode(swimmerCode);
		swimmerResponse.setFirstName("Finn");
		swimmerResponse.setLastName("De Peuter");
		swimmerResponse.setBestTimes(bestTimeResponse);

		Race race = new Race();
		race.setId(1L);
		race.setRaceId("1");
		race.setName(name);
		race.setDate(date);
		race.setSwimmerLastName("De Peuter");
		race.setSwimmerFirstName("Finn");
		race.setEventName("50m freestyle");

		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(anyString(), any(Function.class))).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EventResponse[].class)).thenReturn(Mono.just(new EventResponse[]{eventResponse}));
		when(responseSpec.bodyToMono(SwimmerResponse[].class)).thenReturn(Mono.just(new SwimmerResponse[]{swimmerResponse}));
		// Act
		boolean result = raceService.registerRace(raceRequest);
		// Assert
		assertFalse(result);

		verify(raceRepository, times(0)).save(race);
	}

	@Test
	void testDeleteRace() {
		// Arrange
		String raceId = "1";
		Race mockRace = new Race();
		when(raceRepository.findRaceByRaceId(raceId)).thenReturn(mockRace);

		// Act
		boolean result = raceService.deleteRace(raceId);

		// Assert
		assertTrue(result);
		verify(raceRepository, times(1)).deleteById(mockRace.getId());
	}

	// Add more tests as needed for other methods in the RaceService class
}
