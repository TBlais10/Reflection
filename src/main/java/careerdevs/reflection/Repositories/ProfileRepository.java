package careerdevs.reflection.Repositories;

import careerdevs.reflection.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser_id(long id);

    Void deleteByUser_id(Long id);
}
