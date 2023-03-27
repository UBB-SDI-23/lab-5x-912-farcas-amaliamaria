package a1.Service;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.getIndividual.ShelterListDTO;
import a1.DTO.join.ShelterDTO;

import java.util.List;

public interface IShelterService {
    List<ShelterIdDTO> getShelters();
    ShelterListDTO getShelterById(Long shelterId);
    void addNewShelter(ShelterDTO shelterDTO);
    void deleteShelter(Long shelterId);
    void updateShelter(ShelterDTO shelterDTO);
    List<ShelterIdDTO> getSheltersGreaterCapacity(Integer capacity);
}
