package dat3.car.service;

import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import dat3.car.security.repository.UserWithRolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    public MemberRepository memberRepository;

    MemberService memberService;

    boolean dataIsReady = false;
    @BeforeEach
    void setUp() {
        if(!dataIsReady){  //Explain this
            memberRepository.save(new Member("m1", "test12", "m1@a.dk",  "bb", "Olsen", "xx vej 34", "Lyngby", "2800"));
            memberRepository.save(new Member("m2", "test12", "m2@a.dk", "aa", "hansen", "xx vej 34", "Lyngby", "2800"));
            dataIsReady = true;
            memberService = new MemberService(memberRepository,null); //Real DB is mocked away with H2
        }
    }
    @Test
    void getMembersAdmin() {
        List<MemberResponse> members = memberService.getMembers(true);
        assertEquals(2,members.size());
    }

}