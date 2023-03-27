package a1.Repository;

import a1.Domain.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Optional<Volunteer> findVolunteerByEmail(String email);

    Optional<Volunteer> findShelterByCode(Long code);
}
