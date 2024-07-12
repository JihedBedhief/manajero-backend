package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Dto.sectionDto;
import manajero.manajerotdddynamicintro.Entity.Accordion;
import manajero.manajerotdddynamicintro.Entity.Section;
import manajero.manajerotdddynamicintro.Repository.AccordionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccordionService {

    @Autowired
    AccordionRepository accordionRepository;


    public List<Accordion> getAllItems() {
        return accordionRepository.findAll();
    }

    public void addItem(Accordion item) {
        accordionRepository.save(item);
    }

    public Accordion updateItem(String id, Accordion updatedItem) {
        return accordionRepository.findById(id)
                .map(item -> {
                    item.setTitle(updatedItem.getTitle());
                    item.setContent(updatedItem.getContent());
                    return accordionRepository.save(item);
                }).orElseThrow(() -> new NullPointerException("Item not found"));
    }

    public void deleteItem(String id) {
        accordionRepository.deleteById(id);
    }

    public Accordion getAccordionById(String id){
        Optional<Accordion> optionalAccordion = accordionRepository.findById(id);
        if (optionalAccordion.isPresent()){
            return optionalAccordion.get();
        }else {
            return null;
        }

    }
}
