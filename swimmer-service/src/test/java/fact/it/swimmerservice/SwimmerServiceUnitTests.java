package fact.it.swimmerservice;

import fact.it.swimmerservice.dto.SwimmerRequest;
import fact.it.swimmerservice.dto.SwimmerResponse;
import fact.it.swimmerservice.model.Swimmer;
import fact.it.swimmerservice.repository.SwimmerRepository;
import fact.it.swimmerservice.service.SwimmerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SwimmerServiceUnitTests {

	@Mock
	private SwimmerRepository swimmerRepository;

	@InjectMocks
	private SwimmerService swimmerService;

	@Test
	void testCreateSwimmer() {
		// Arrange
		SwimmerRequest swimmerRequest = new SwimmerRequest("johndoe2000", "John", "Doe", 2000, "Moska");

		// Act
		swimmerService.createSwimmer(swimmerRequest);

		// Assert
		verify(swimmerRepository, times(1)).save(any(Swimmer.class));
	}

	@Test
	void testGetAllSwimmers() {
		// Arrange
		List<Swimmer> mockSwimmers = Arrays.asList(
				new Swimmer("1", "Bob", "Jansens", "Moska", "bobjansens2001", 2001, null),
				new Swimmer("2", "Finn", "De Peuter", "HZA", "finndepeuter2001", 2001, null)
		);
		when(swimmerRepository.findAll()).thenReturn(mockSwimmers);

		// Act
		List<SwimmerResponse> result = swimmerService.getAllSwimmers();

		// Assert
		assertEquals(mockSwimmers.size(), result.size());
		assertEquals("finndepeuter2001", result.get(1).getSwimmerCode());
		assertEquals("Finn", result.get(1).getFirstName());
		assertEquals("De Peuter", result.get(1).getLastName());
		assertEquals("HZA", result.get(1).getClub());

		verify(swimmerRepository, times(1)).findAll();
		// Add more assertions based on your specific requirements
	}

	@Test
	void testGetSwimmerBySwimmerCode() {
		// Arrange
		String swimmerCode = "bobjansens2001";
		List<Swimmer> mockSwimmers = Arrays.asList(
				new Swimmer("1", swimmerCode, "Bob", "Jansens", "Moska", 2001, null)
		);
		when(swimmerRepository.findSwimmerBySwimmerCode(swimmerCode)).thenReturn(mockSwimmers);

		// Act
		List<SwimmerResponse> result = swimmerService.getSwimmerBySwimmerCode(swimmerCode);

		// Assert
		assertEquals(mockSwimmers.size(), result.size());
		// Add more assertions based on your specific requirements
	}

	@Test
	void testUpdateSwimmer() {
		// Arrange
		SwimmerRequest swimmerRequest = new SwimmerRequest("bobjansens2001", "UpdatedFirstName", "UpdatedLastName", 2000, "updatedClub");
		when(swimmerRepository.findSwimmerBySwimmerCode("bobjansens2001"))
				.thenReturn(Arrays.asList(new Swimmer("1", "Bob", "Jansens", "Moska", "bobjansens2001", 2001, null)));

		// Act
		boolean result = swimmerService.updateSwimmer(swimmerRequest);

		// Assert
		assertTrue(result);
		verify(swimmerRepository, times(1)).save(any(Swimmer.class));
	}

	// Add more tests as needed for other methods in the SwimmerService class
}

