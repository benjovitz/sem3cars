package dat3.car.config;

import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {

    MemberRepository memberRepository;

    public DeveloperData(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    private final String passwordUsedByAll = "test12";
    private final List<String> colors = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        colors.add(0,"yellow");
        colors.add(1,"blue");
        Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Member m2 = new Member("member2", passwordUsedByAll, "aaa@dd.dk", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        m1.setFavoriteCarColors(colors);
        memberRepository.save(m1);
        memberRepository.save(m2);
    }

}
