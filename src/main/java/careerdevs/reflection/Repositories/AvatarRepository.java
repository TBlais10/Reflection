package careerdevs.reflection.Repositories;

import careerdevs.reflection.Models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
