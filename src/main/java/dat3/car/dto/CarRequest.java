package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequest {

    String brand;
    String model;
    Double pricePrDay;

    Integer bestDiscount;

    public static Car getCarEntity(CarRequest c){
        return new Car(c.brand,c.model,c.pricePrDay);
    }


    public CarRequest(Car c){
        this.brand=c.getBrand();
        this.model=c.getModel();
        this.pricePrDay=c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }

}
