package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate date;


    //Car variables
    String brand;
    Long carID;
    String model;
    //Member variables


    public ReservationResponse(Reservation r){
        this.date=r.getDate();
        this.carID=r.getCar().getId();
        this.brand=r.getCar().getBrand();
        this.model=r.getCar().getModel();


    }
}
