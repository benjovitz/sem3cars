package dat3.car.config;

import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeveloperData implements ApplicationRunner {

    MemberRepository memberRepository;

    public DeveloperData(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
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
        memberRepository.save(m1);
        memberRepository.save(m2);
    }

}
