package dat3.car.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate reservationDate;

    Car car;

    Member member;

    public static Reservation getReservationDateEntity(ReservationRequest r){
        return new Reservation(r.getCar(),r.member,r.getReservationDate());
    }

    public ReservationRequest(Reservation reservation) {
        this.reservationDate = reservation.getDate();
        this.car = reservation.getCar();
        this.member = reservation.getMember();
    }
}
