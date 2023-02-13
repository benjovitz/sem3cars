package dat3.car.service;

import dat3.car.dto.CarRepsonse;
import dat3.car.dto.CarRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public CarRepsonse findCarByID(Long id) {
        String s = String.valueOf(id);
        Optional<Car> c = carRepository.findById(s);
        Car car = c.orElse(null);
        CarRepsonse carRepsonse = new CarRepsonse(car,true);
        return carRepsonse;
    }

    public CarRepsonse addCar(CarRequest request) {
        Car newCar = CarRequest.getCarEntity(request);
        newCar = carRepository.save(newCar);
        return new CarRepsonse(newCar,true);
    }

    public void editCar(long id, CarRequest carRequest) {
      Car carToEdit =  carRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this ID doesnt exist"));
      Optional.ofNullable(carRequest.getBrand()).ifPresent(carToEdit::setBrand);
      Optional.ofNullable(carRequest.getModel()).ifPresent(carToEdit::setModel);
      Optional.ofNullable(carRequest.getPricePrDay()).ifPresent(carToEdit::setPricePrDay);
      carRepository.save(carToEdit);

    }

    public CarRepsonse setDiscount(long id, int value) {
        carRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this ID doesnt exist"));
        Optional<Car> c = carRepository.findById(String.valueOf(id));
        Car car = c.orElse(null);
        car.setBestDiscount(value);
        carRepository.save(car);
        return new CarRepsonse(car,true);
    }

    public void deleteCar(long id) {
        carRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this ID doesnt exist"));
        Optional<Car> c = carRepository.findById(String.valueOf(id));
        Car car = c.orElse(null);
        carRepository.delete(car);
    }
}
