package dat3.car.service;


import dat3.car.dto.ReservationRequest;
import dat3.car.entity.ReservationDate;
import dat3.car.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;


    public ReservationService(ReservationRepository reservationRepository, CarService carService, MemberService memberService) {
        this.reservationRepository = reservationRepository;
    }

    public void makeReservation(ReservationRequest request){
        if(request.getCar().checkDate(request.getReservationDate())==false) {
            ReservationDate newReservationDate = ReservationRequest.getReservationDateEntity(request);
            reservationRepository.save(newReservationDate);
        }
        //return new ReservationRepsonse(newReservationDate,true);

    }
}
