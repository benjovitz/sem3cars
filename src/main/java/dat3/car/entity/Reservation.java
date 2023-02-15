package dat3.car.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne

    Car car;

    @ManyToOne

    Member member;

    LocalDate date;

    public Reservation(Car car, Member member, LocalDate date) {
        this.car = car;
        this.member = member;
        this.date = date;
    }
}
