package a1.Controller;

import a1.DTO.getAll.AnimalIdDTO;
import a1.DTO.getIndividual.AnimalDTO;
import a1.DTO.join.AnimalForVolunteersDTO;
import a1.Domain.Animal;
import a1.Domain.MedicalRecord;
import a1.Domain.Shelter;
import a1.Service.AnimalService;
import a1.Service.IAnimalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Validated
@RequestMapping(path="api/animal")
public class AnimalController {
    @PersistenceContext
    private EntityManager entityManager;
    private final IAnimalService animalService;

    @Autowired
    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalIdDTO> getAnimals(){
        return animalService.getAnimals();
    }

    /*@GetMapping("/")
    public List<Optional<Animal>> getAnimalsFilter(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) Long microchipNumber) {
        return animalService.getAnimalsFilter(name, microchipNumber);
    }*/

    @GetMapping(path = "{animalId}")
    public AnimalDTO getAnimalById(@PathVariable("animalId") Long animalId){
        return animalService.getAnimalById(animalId);
    }

    @PostMapping
    public ResponseEntity<AnimalForVolunteersDTO> addNewAnimal(@Valid @RequestBody AnimalForVolunteersDTO animal){

      /* AnimalForVolunteersDTO animalAdded = animalService.addNewAnimal(new Animal(animal.getMicrochipNumber(), animal.getName(), entityManager.find(MedicalRecord.class, animal.getMedicalRecord()), entityManager.find(Shelter.class, animal.getShelter()), animal.getDayBroughtIn(), animal.getDayBroughtIn()));*/
        //return new ResponseEntity<>(animalService.addNewAnimal(animal), HttpStatus.CREATED);

        AnimalForVolunteersDTO itemDtoResponse=animalService.addNewAnimal(animal);
        return ResponseEntity.ok(itemDtoResponse);
        //return ResponseEntity.ok("User is valid");
    }

    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long animalId){
        animalService.deleteAnimal(animalId);
    }

    @PutMapping
    public void updateAnimal(@RequestBody AnimalForVolunteersDTO animal){
        animalService.updateAnimal(animal);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
