package manajero.manajerotdddynamicintro.Services;

import jakarta.annotation.PostConstruct;
import manajero.manajerotdddynamicintro.Dto.sectionDto;
import manajero.manajerotdddynamicintro.Entity.Section;
import manajero.manajerotdddynamicintro.Repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;



    public sectionDto addSection(sectionDto sectionDto) throws IOException {
        Section section = new Section();
        section.setId(sectionDto.getId());
        section.setTitle(sectionDto.getTitle());
        section.setDescription(sectionDto.getDescription());
        section.setImg(sectionDto.getImg().getBytes());
        return sectionRepository.save(section).getDto();
    }

    public List<sectionDto> getAllSection(){
        List<Section> sections = sectionRepository.findAll();
        return sections.stream().map(Section::getDto).collect(Collectors.toList());
    }

    public sectionDto getSectionById(String id){
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isPresent()){
            return optionalSection.get().getDto();
        }else {
            return null;
        }

    }


    public sectionDto updateSection(String id , sectionDto sectionDto) throws IOException {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isPresent() ){
            Section section = optionalSection.get();
            section.setTitle(sectionDto.getTitle());
            section.setDescription(sectionDto.getDescription());
            if (sectionDto.getImg() != null){
                section.setImg(sectionDto.getImg().getBytes());
            }
            return sectionRepository.save(section).getDto();
        }else {
            return null;
        }
    }

    public boolean deleteSection(String id){
        Optional<Section> optionalSection= sectionRepository.findById(id);
        if(optionalSection.isPresent()){
            sectionRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }


}
