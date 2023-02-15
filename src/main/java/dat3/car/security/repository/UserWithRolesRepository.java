package dat3.car.security.repository;

import dat3.car.security.entity.UserWithRoles;
import dat3.car.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles,String> {
    Boolean existsByEmail(String email);
}
