package careerdevs.reflection.Repositories;

import careerdevs.reflection.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
