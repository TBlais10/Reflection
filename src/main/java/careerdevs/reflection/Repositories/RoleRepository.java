package careerdevs.reflection.Repositories;

import careerdevs.reflection.Auth.ERole;
import careerdevs.reflection.Auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
