package dat3.car.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.ReservationDate;
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

    public static ReservationDate getReservationDateEntity(ReservationRequest r){
        return new ReservationDate(r.getCar(),r.member,r.getReservationDate());
    }

    public ReservationRequest(ReservationDate reservationDate) {
        this.reservationDate = reservationDate.getDate();
        this.car = reservationDate.getCar();
        this.member = reservationDate.getMember();
    }
}
