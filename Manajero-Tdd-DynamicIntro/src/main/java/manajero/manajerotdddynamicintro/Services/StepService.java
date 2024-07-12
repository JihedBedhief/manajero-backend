package manajero.manajerotdddynamicintro.Services;

import lombok.extern.slf4j.Slf4j;
import manajero.manajerotdddynamicintro.Entity.Limitation;
import manajero.manajerotdddynamicintro.Entity.Step;
import manajero.manajerotdddynamicintro.Repository.StepRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StepService {

    @Autowired
    StepRepositoy stepRepositoy;

    public List<Step> getAll() {
        return stepRepositoy.findAll();
    }

    public Step save(Step entity) {
        log.info("bodu"+entity);
        return stepRepositoy.save(entity);

    }
    public void delete(String id) {
        stepRepositoy.deleteById(id);
    }



}
