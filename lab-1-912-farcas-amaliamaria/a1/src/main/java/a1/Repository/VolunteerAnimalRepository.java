package a1.Repository;

import a1.Domain.VolunteerAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerAnimalRepository extends JpaRepository<VolunteerAnimal, Long> {
}
