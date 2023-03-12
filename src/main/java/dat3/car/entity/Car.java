package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation r){
        if(reservations ==null){
            reservations =new HashSet<>();
        }
        reservations.add(r);
        r.setCar(this);
    }

    public boolean checkDate(LocalDate date){
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()){
            Reservation reservation = iterator.next();
            if(reservation.date.equals(date)){
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

    public Car(String brand, String model, double pricePrDay, int bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
