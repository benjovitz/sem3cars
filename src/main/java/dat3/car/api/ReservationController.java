package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.service.CarService;
import dat3.car.service.MemberService;
import dat3.car.service.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;
    CarService carService;
    MemberService memberService;

    public ReservationController(ReservationService reservationService, CarService carService, MemberService memberService) {
        this.reservationService = reservationService;
        this.carService = carService;
        this.memberService = memberService;
    }

    @GetMapping
    List<ReservationResponse> getReservations(){
        return reservationService.getReservations();
    }

    @PostMapping("/{username}/{carID}")
    ReservationResponse makeReservation(@RequestBody ReservationRequest body,@PathVariable String username,@PathVariable Long carID){
        System.out.println("----------api----------");
        Car car = carService.findCar(carID);
        Member member = memberService.findMember(username);
        body.setCar(car);
        body.setMember(member);
        System.out.println("----------RESPONSE----------");
        return reservationService.makeReservation(body);
    }
}
