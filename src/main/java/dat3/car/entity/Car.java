package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "car_brand",nullable = false)
    private String brand;

    @Column(name = "car_model",nullable = false)
    private String model;

    @Column(name = "rental_price_day")
    private double pricePrDay;

    @Column(name = "max_discount")
    private int bestDiscount;

    @CreationTimestamp
    LocalDateTime created;
    @UpdateTimestamp
    LocalDateTime lastEdited;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<ReservationDate> reservationDates = new HashSet<>();

    public void addReservation(ReservationDate r){
        if(reservationDates==null){
            reservationDates=new HashSet<>();
        }
        reservationDates.add(r);
    }

    public boolean checkDate(LocalDate date){
        Iterator<ReservationDate> iterator = reservationDates.iterator();
        while (iterator.hasNext()){
            ReservationDate reservationDate = iterator.next();
            if(reservationDate.date.equals(date)){
                System.out.println("Date already exists");
                return true;
            }
        }
        return false;
    }


    public Car (String brand, String model, Double pricePrDay){
        this.brand=brand;
        this.model=model;
        this.pricePrDay=pricePrDay;
    }

}
