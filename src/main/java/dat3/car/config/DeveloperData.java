package dat3.car.config;

import dat3.car.dto.ReservationRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.car.service.ReservationService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeveloperData implements ApplicationRunner {

    MemberRepository memberRepository;
    CarRepository carRepository;

    ReservationRepository reservationRepository;

    ReservationService reservationService;

    public DeveloperData(MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository, ReservationService reservationService) {
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    private final String passwordUsedByAll = "test12";
    private List<String> colors = new ArrayList<>();
    private Map<String,String> phone = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        colors.add(0,"yellow");
        colors.add(1,"blue");
        phone.put("work","88888888");
        phone.put("mobile","12345678");
        phone.put("home","24681012");

        Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Member m2 = new Member("member2", passwordUsedByAll, "aaa@dd.dk", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        m1.setFavoriteCarColors(colors);
        m2.setPhones(phone);
        System.out.println("----------member----------");
        memberRepository.save(m1);
        memberRepository.save(m2);

        Car c1 = new Car("Toyota", "Camry", 50.0);
        Car c2 = new Car("Honda", "Civic", 40.0);
        Car c3 = new Car("Ford", "Mustang", 60.0);
        Car c4 = new Car("Chevrolet", "Camaro", 55.0);
        Car c5 = new Car("Tesla", "Model 3", 70.0);
        Car c6 = new Car("Nissan", "Altima", 45.0);
        Car c7 = new Car("Mazda", "CX-5", 55.0);
        Car c8 = new Car("Subaru", "Outback", 50.0);
        Car c9 = new Car("Jeep", "Wrangler", 60.0);
        Car c10 = new Car("Dodge", "Challenger", 65.0);

        System.out.println("----------car----------");
        c1.setBestDiscount(10);
        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);
        carRepository.save(c4);
        carRepository.save(c5);
        carRepository.save(c6);
        carRepository.save(c7);
        carRepository.save(c8);
        carRepository.save(c9);
        carRepository.save(c10);

        System.out.println("----------Reservationdate----------");
        Reservation r1 = Reservation.builder().member(m1).car(c1).date(LocalDate.now()).build();
        Reservation r2 = Reservation.builder().member(m2).car(c1).date(LocalDate.now()).build();


        //reservationDateRepository.save(r1);
        ReservationRequest reservationRequest1 = new ReservationRequest(r1);
        ReservationRequest reservationRequest2 = new ReservationRequest(r2);

        reservationService.makeReservation(reservationRequest1);
        reservationService.makeReservation(reservationRequest2);

    }

}
