package manajero.manajerotddtasksmanagment.Controllers;

import manajero.manajerotddtasksmanagment.Entities.Archive;
import manajero.manajerotddtasksmanagment.Entities.Task;
import manajero.manajerotddtasksmanagment.Repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/archive")
@CrossOrigin(origins = "http://localhost:4200")
public class ArchiveController {


    @Autowired
    ArchiveRepository archiveRepository;

    @GetMapping
    public List<Archive> getAllItems() {
        return archiveRepository.findAll();
    }
}
