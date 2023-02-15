package dat3.car.service;


import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.ReservationDate;
import dat3.car.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Service

public class ReservationService {

    ReservationRepository reservationRepository;


    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse makeReservation(ReservationRequest request){
        if(!request.getCar().checkDate(request.getReservationDate()) && validityDateCheck(request.getReservationDate())){
            ReservationDate newReservationDate = ReservationRequest.getReservationDateEntity(request);
            reservationRepository.save(newReservationDate);
            request.getCar().addReservation(newReservationDate);
            request.getMember().addReservation(newReservationDate);
            return new ReservationResponse(newReservationDate);
        }
        return null;

    }
    public boolean validityDateCheck(LocalDate date){
        if(date.isBefore(LocalDate.now())){
            System.out.println("date is in the past");
            return false;
        }
        return true;
    }
}
