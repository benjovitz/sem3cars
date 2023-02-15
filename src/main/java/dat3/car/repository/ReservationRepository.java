package dat3.car.repository;

import dat3.car.entity.ReservationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDate,String> {
}
