package manajero.manajerotddtasksmanagment;


import manajero.manajerotddtasksmanagment.Entities.Tests;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import manajero.manajerotddtasksmanagment.Services.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestServiceTest {
    @InjectMocks
    private TestService testService;

    @Mock
    private TestRepository testRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        Tests test1 = new Tests();
        test1.setId("1");
        test1.setTitle("Test 1");

        Tests test2 = new Tests();
        test2.setId("2");
        test2.setTitle("Test 2");

        when(testRepository.findAll()).thenReturn(Arrays.asList(test1, test2));

        List<Tests> tests = testService.getAll();

        assertThat(tests).hasSize(2);
        assertThat(tests).contains(test1, test2);
    }

    @Test
    public void testSave() {
        Tests test = new Tests();
        test.setTitle("Test 1");
        test.setDescription("Description 1");

        when(testRepository.save(any(Tests.class))).thenReturn(test);

        Tests savedTest = testService.save(test);

        assertThat(savedTest).isNotNull();
        assertThat(savedTest.getTitle()).isEqualTo("Test 1");
        assertThat(savedTest.getDescription()).isEqualTo("Description 1");
        assertThat(savedTest.getStatus()).isFalse();
    }

    @Test
    public void testDelete() {
        String testId = "1";
        testService.delete(testId);

        verify(testRepository).deleteById(testId);
    }

    @Test
    public void testGetById() {
        Tests test = new Tests();
        test.setId("1");
        test.setTitle("Test 1");

        when(testRepository.findById("1")).thenReturn(Optional.of(test));

        Optional<Tests> foundTest = testService.getById("1");

        assertThat(foundTest).isPresent();
        assertThat(foundTest.get().getId()).isEqualTo("1");
        assertThat(foundTest.get().getTitle()).isEqualTo("Test 1");
    }

    @Test
    public void testUpdate() {
        Tests existingTest = new Tests();
        existingTest.setId("1");
        existingTest.setTitle("Existing Test");

        Tests newTest = new Tests();
        newTest.setTitle("Updated Test");
        newTest.setDescription("Updated Description");

        when(testRepository.findById("1")).thenReturn(Optional.of(existingTest));
        when(testRepository.save(any(Tests.class))).thenReturn(existingTest);

        Tests updatedTest = testService.update("1", newTest);

        assertThat(updatedTest.getTitle()).isEqualTo("Updated Test");
        assertThat(updatedTest.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    public void testUpdateThrowsExceptionWhenNotFound() {
        Tests newTest = new Tests();
        newTest.setTitle("Updated Test");
        newTest.setDescription("Updated Description");

        when(testRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            testService.update("1", newTest);
        });
    }
}
