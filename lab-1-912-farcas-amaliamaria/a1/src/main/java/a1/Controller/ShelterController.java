package a1.Controller;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.getIndividual.ShelterListDTO;
import a1.DTO.join.ShelterDTO;
import a1.DTO.reports.AverageAgeVolunteersShelterDTO;
import a1.DTO.reports.VaccinatedAnimalsRatioSheltersDTO;
import a1.Service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path="api/shelter")
public class ShelterController {

    private final ShelterService shelterService;


    @Autowired
    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping
    public List<ShelterIdDTO> getAnimals(){
        return shelterService.getShelters();
    }

    /*@GetMapping("/")
    public List<Optional<Shelter>> getSheltersFilter(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String city) {
        return shelterService.getSheltersFilter(name, city);
    }*/

    @GetMapping("/greaterCapacity")
    public List<ShelterIdDTO> getSheltersGreaterCapacity(@RequestParam(required = true) Integer capacity) {
        return shelterService.getSheltersGreaterCapacity(capacity);
    }

    @GetMapping(path = "{shelterId}")
    public ShelterListDTO getShelterById(@PathVariable("shelterId") Long shelterId){
        return shelterService.getShelterById(shelterId);
    }

    @PostMapping
    public void addNewShelter(@RequestBody ShelterDTO shelter){
        shelterService.addNewShelter(shelter);
    }

    @DeleteMapping(path = "{shelterId}")
    public void deleteShelter(@PathVariable("shelterId") Long shelterId){
        shelterService.deleteShelter(shelterId);
    }

    @PutMapping
    public void updateShelter(@RequestBody ShelterDTO shelter){
        shelterService.updateShelter(shelter);
    }


    @GetMapping("/reportVolunteers")
    public List<AverageAgeVolunteersShelterDTO> generateReportVolunteer(){
        return shelterService.exportReportVolunteer();
    }

    @GetMapping("/reportAnimals")
    public List<VaccinatedAnimalsRatioSheltersDTO> generateReportAnimal(){
        return shelterService.exportReportAnimal();
    }

}
