package dat3.car.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRepsonse {

    Long id;
    String brand;
    String model;
    Double pricePrDay;
    Integer bestDiscount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    Set<ReservationResponse> reservations;

    public CarRepsonse(Car c, boolean includeAll){
        this.brand=c.getBrand();
        this.model=c.getModel();
        this.pricePrDay=c.getPricePrDay();
        if(includeAll){
            this.id=c.getId();
            this.created=c.getCreated();
            this.bestDiscount=c.getBestDiscount();
            if (c.getReservations() != null) {
                Set<ReservationResponse> reservationResponses = new HashSet<>();
                for (Reservation reservation : c.getReservations()) {
                    reservationResponses.add(new ReservationResponse(reservation,false,true));
                }
                this.reservations = reservationResponses;
            }
        }
    }
}
