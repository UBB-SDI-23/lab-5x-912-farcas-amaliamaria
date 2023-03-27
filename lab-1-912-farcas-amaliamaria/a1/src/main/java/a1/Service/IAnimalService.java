package a1.Service;

import a1.DTO.getAll.AnimalIdDTO;
import a1.DTO.getIndividual.AnimalDTO;
import a1.DTO.join.AnimalForVolunteersDTO;
import a1.Domain.Animal;

import java.util.List;

public interface IAnimalService {
    List<AnimalIdDTO> getAnimals();
    AnimalDTO getAnimalById(Long entityId);
    String addNewAnimal(AnimalForVolunteersDTO entityDTO);
    void deleteAnimal(Long entityId);
    void updateAnimal(AnimalForVolunteersDTO entityDTO);
}
