package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.WhatIf;
import manajero.manajerotdddynamicintro.Entity.Why;
import manajero.manajerotdddynamicintro.Services.WhatIfService;
import manajero.manajerotdddynamicintro.Services.WhyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/whatif")
@CrossOrigin(origins = "http://localhost:4200")
public class WhatIfController {
    @Autowired
    private WhatIfService service;

    @GetMapping
    public List<WhatIf> getAll() {
        return service.getAll();
    }

    @PostMapping
    public WhatIf save(@RequestBody WhatIf entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }


    @GetMapping("/{id}")
    public Optional<WhatIf> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public WhatIf update(@PathVariable String id, @RequestBody WhatIf newEntity) {
        return service.update(id, newEntity);
    }


}
