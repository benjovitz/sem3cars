package dat3.car.api;

import dat3.car.dto.CarRepsonse;
import dat3.car.dto.CarRequest;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService){
        this.carService=carService;
    }

    //Admin
    @GetMapping
    List<CarRepsonse> getCars(){
        return carService.getCars(true);
    }
    @GetMapping(path = "/{Id}")
    CarRepsonse getCarById(@PathVariable Long Id ){
        return carService.findCarByID(Id);
    }
    //Admin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarRepsonse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Admin
    @PutMapping("/{Id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable Long Id){
        carService.editCar(Id,body);
        return ResponseEntity.ok(true);
    }

    //Admin
    @PatchMapping("/discount/{Id}/{value}")
    CarRepsonse setBestDiscount(@PathVariable Long Id, @PathVariable int value) {
        return carService.setDiscount(Id,value);
    }

    //Admin
    @DeleteMapping("/{Id}")
    void deleteCarByUsername(@PathVariable Long Id) {
        carService.deleteCar(Id);
    }
}

