package manajero.manajerotddtasksmanagment.Controllers;



import manajero.manajerotddtasksmanagment.Entities.Tests;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import manajero.manajerotddtasksmanagment.Services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @Autowired
    private TestService service;
    @Autowired
    private TestRepository testRepository;

    @GetMapping
    public List<Tests> getAll() {
        return service.getAll();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Tests save(@RequestPart("title") String title,
                      @RequestPart("description") String description) {

        Tests test = new Tests();
        test.setTitle(title);
        test.setDescription(description);
        test.setStatus(false);
        return service.save(test);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
    @GetMapping("/{id}" )
    public Optional<Tests> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}" )
    public Tests update(@PathVariable String id, @ModelAttribute Tests t) {
        Tests test = new Tests();
        //test.setTitle(title);
       // test.setDescription(description);

        return service.update(id, t);
    }

    @GetMapping("/Kpis")
    public ResponseEntity<Map<String, Object>> getTestKPIs() {
        Map<String, Object> kpis = new HashMap<>();

        long totalTests = testRepository.count();
        long passedTests = testRepository.countByStatus(true);
        long failedTests = testRepository.countByStatus(false);
        double testPassRate = (totalTests > 0) ? (passedTests / (double) totalTests) * 100 : 0;
      //  double avgTestDuration = testRepository.calculateAverageDuration();

        kpis.put("totalTests", totalTests);
        kpis.put("passedTests", passedTests);
        kpis.put("failedTests", failedTests);
        kpis.put("testPassRate", testPassRate);
      //  kpis.put("avgTestDuration", avgTestDuration);

        return ResponseEntity.ok(kpis);
    }

}
