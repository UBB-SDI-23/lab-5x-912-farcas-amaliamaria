package a1.Repository;

import a1.Domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT s FROM Animal s WHERE s.microchipNumber = ?1")
    Optional<Animal> findAnimalByMicrochip(Long microchipNumber);

    @Query("SELECT s FROM Animal s WHERE s.name = ?1")
    Optional<Animal> findAnimalByName(String name);
}
