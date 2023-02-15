package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.ReservationDate;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void makeReservationSuccess(){
        Car car = new Car("HEj","Hej",200.0);
        Member newMember = new Member("user", "mypassword", "johndoe@example.com",
                "John", "Doe", "123 Main St", "Anytown", "12345");
        ReservationRequest reservationRequest = new ReservationRequest(LocalDate.now(),car,newMember);
        ReservationResponse testDate = reservationService.makeReservation(reservationRequest);
        //Mockito.when(reservationRepository.findById(String.valueOf(1))).thenReturn(Optional.ofNullable(testDate));

        assertEquals("user",testDate.getMember().getUsername());
    }
    @Test
    void makeReservationError(){
        Car car = new Car("HEj","Hej",200.0);
        Member newMember = new Member("user", "mypassword", "johndoe@example.com",
                "John", "Doe", "123 Main St", "Anytown", "12345");
        ReservationRequest reservationRequest = new ReservationRequest(LocalDate.of(2020,02,02),car,newMember);
        ReservationResponse testDate = reservationService.makeReservation(reservationRequest);
        //Mockito.when(reservationRepository.findById(String.valueOf(1))).thenReturn(Optional.ofNullable(testDate));

        assertNull(testDate);
    }
}