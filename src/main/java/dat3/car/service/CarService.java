package dat3.car.service;

import dat3.car.dto.CarRepsonse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository=carRepository;
    }
    public List<CarRepsonse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();
        List<CarRepsonse> carRepsonses = cars.stream().map(car -> new CarRepsonse(car,includeAll)).toList();
        return carRepsonses;
    }
}
