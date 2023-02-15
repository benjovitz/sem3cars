package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    String username; //Remember this is the primary key
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;
    Integer ranking;
    Boolean approved;

    Map<String,String> phones;
    Set<ReservationResponse> reservations;

    //Convert Member Entity to Member DTO
    public MemberResponse(Member m, boolean includeAll, boolean includeReservation) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();
        this.phones = m.getPhones();

        if (includeAll) {
            this.created = m.getCreated();
            this.approved = m.isApproved();
            this.ranking = m.getRanking();
        }

        if (includeReservation && m.getReservations() != null) {
            Set<ReservationResponse> reservationResponses = new HashSet<>();
            for (Reservation reservation : m.getReservations()) {
                reservationResponses.add(new ReservationResponse(reservation));
            }
            this.reservations = reservationResponses;
        }
    }

}

