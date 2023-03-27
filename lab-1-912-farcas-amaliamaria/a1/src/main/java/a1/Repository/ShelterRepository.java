package a1.Repository;

import a1.Domain.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    @Query("SELECT s FROM Shelter s WHERE s.city = ?1")
    Optional<Shelter> findShelterByCity(String city);

    @Query("SELECT s FROM Shelter s WHERE s.name = ?1")
    Optional<Shelter> findShelterByName(String name);

    @Query("SELECT s FROM Shelter s WHERE s.phoneNumber = ?1")
    Optional<Shelter> findShelterByPhoneNumber(Long phoneNumber);

    @Query("SELECT s FROM Shelter s WHERE s.postalCode = ?1")
    Optional<Shelter> findShelterByPostalCode(Long postalCode);

    @Query("SELECT s FROM Shelter s WHERE s.capacity > ?1")
    List<Optional<Shelter>> getSheltersGreaterCapacity(Integer capacity);
}
