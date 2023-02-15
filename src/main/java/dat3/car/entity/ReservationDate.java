package dat3.car.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class ReservationDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    Car car;

    @ManyToOne
    Member member;

    LocalDate date;

    public ReservationDate(Car car, Member member, LocalDate date) {
        this.car = car;
        this.member = member;
        this.date = date;
    }
}
