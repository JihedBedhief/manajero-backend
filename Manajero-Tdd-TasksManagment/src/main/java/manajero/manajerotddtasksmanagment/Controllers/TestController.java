package manajero.manajerotddtasksmanagment.Controllers;



import manajero.manajerotddtasksmanagment.Entities.Tests;
import manajero.manajerotddtasksmanagment.Services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @Autowired
    private TestService service;

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
}
