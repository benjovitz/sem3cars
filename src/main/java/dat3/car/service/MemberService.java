package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }


    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = members.stream().map(m->new MemberResponse(m,includeAll)).toList();
        return memberResponses;
    }

    public MemberResponse addMember(MemberRequest memberRequest){
        //Later you should add error checks --> Missing arguments, email taken etc.
        if(memberRepository.existsById(memberRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this ID already exist");
        }
        if(memberRepository.existsByEmail(memberRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this Email already exist");
        }

        Member newMember = MemberRequest.getMemberEntity(memberRequest);
        newMember = memberRepository.save(newMember);

        return new MemberResponse(newMember, false);
    }
    public MemberResponse findMemberByUsername(String username){
       Optional<Member> m = memberRepository.findById(username);
       Member member = m.orElse(null);
       MemberResponse mr = new MemberResponse(member,true);
       return mr;
    }
}
