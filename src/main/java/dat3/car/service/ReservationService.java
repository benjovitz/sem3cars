package dat3.car.service;


import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Reservation;
import dat3.car.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class ReservationService {

    ReservationRepository reservationRepository;


    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse makeReservation(ReservationRequest request){
        if(!request.getCar().checkDate(request.getReservationDate()) && validityDateCheck(request.getReservationDate())){
            Reservation newReservation = ReservationRequest.getReservationDateEntity(request);
            reservationRepository.save(newReservation);
            request.getCar().addReservation(newReservation);
            request.getMember().addReservation(newReservation);
            return new ReservationResponse(newReservation,true,true);
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

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(ReservationResponse::new).toList();
    }
}
