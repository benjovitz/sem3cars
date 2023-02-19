package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {

    //opgave 8.A
    List<Car> findCarsByBrandAndModel(String brand, String model);

    //8.C
   @Query("SELECT avg (c.pricePrDay)FROM Car c")
    Double getAverageOfPricePrDay();

   //8.D
   @Query("SELECT c FROM Car c ORDER BY c.bestDiscount DESC")
   List<Car> bestDiscountOrder();

   //8.F
   List<Car> findCarsByReservationsIsNull();
}
