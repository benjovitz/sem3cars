package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dat3.car.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;



    public Member(String user, String password, String email,
                  String firstName, String lastName, String street, String city, String zip) {
        super(user,password,email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
    @CreationTimestamp
    LocalDateTime created;
    @UpdateTimestamp
    LocalDateTime lastEdited;

    @ElementCollection
    List<String> favoriteCarColors = new ArrayList<>();

    @ElementCollection
    @MapKeyColumn(name = "description")
    @Column(name = "phone_number")
    Map<String,String> phones = new HashMap<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation r){
        if(reservations ==null){
            reservations =new HashSet<>();
        }
        reservations.add(r);
        r.setMember(this);
    }




}
