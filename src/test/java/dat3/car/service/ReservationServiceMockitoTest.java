package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ReservationServiceMockitoTest {

    @Mock
    ReservationRepository reservationRepository;

    ReservationService reservationService;

    @BeforeEach
    void setup(){
        reservationService=new ReservationService(reservationRepository);
    }

    @Test
    void checkPastDate(){
        LocalDate past =LocalDate.of(2020,02,04);
        assertEquals(false,reservationService.validityDateCheck(past));

    }
    @Test
    void makeReservationError(){
        Car car = new Car("HEj","Hej",200.0);
        Member newMember = new Member("user", "mypassword", "johndoe@example.com",
                "John", "Doe", "123 Main St", "Anytown", "12345");
        ReservationRequest reservationRequest = new ReservationRequest(LocalDate.of(2020,02,02),car,newMember);
        ReservationResponse testDate = reservationService.makeReservation(reservationRequest);
        assertNull(testDate);
    }
    @Test
    void numberOfReservations(){
        Car car = new Car("HEj","Hej",200.0);
        Member member = new Member("user", "mypassword", "johndoe@example.com",
                "John", "Doe", "123 Main St", "Anytown", "12345");
        Reservation reservation = new Reservation(car,member,LocalDate.now());
        List<Reservation> list = new ArrayList<>();
        list.add(reservation);
        Mockito.when(reservationRepository.findReservationsByMember(member)).thenReturn(list);
        assertEquals(1,reservationService.numberOfReservations(member));
    }
}