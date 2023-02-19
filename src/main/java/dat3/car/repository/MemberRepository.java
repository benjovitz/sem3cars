package dat3.car.repository;

import dat3.car.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {

    boolean existsByEmail(String email);

    //8.E
    List<Member> findMembersByReservationsIsNotNull();
}
