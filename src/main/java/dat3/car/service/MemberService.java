package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public Member findMember(String username){
        memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));
        Optional<Member> m = memberRepository.findById(username);
        Member member = m.orElse(null);
        return member;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = members.stream().map(m->new MemberResponse(m,includeAll,false)).toList();
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

        return new MemberResponse(newMember, false,false);
    }

    public MemberResponse findMemberByUsername(String username){
       Member member = findMember(username);
       MemberResponse mr = new MemberResponse(member,true,true);
       return mr;
    }
    public void deleteMember(String username){
        memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));
            Optional<Member> m = memberRepository.findById(username);
            Member member = m.orElse(null);
            memberRepository.delete(member);
    }
    public MemberResponse setRankingForUser(String username, int value) {
        memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));
        Optional<Member> m = memberRepository.findById(username);
        Member member = m.orElse(null);
        member.setRanking(value);
        memberRepository.save(member);
        MemberResponse mr = new MemberResponse(member,true,false);
        return mr;
    }

    public void editMember(String username, MemberRequest memberRequest) {
        Member memberToEdit = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));

        Optional.ofNullable(memberRequest.getEmail()).ifPresent(memberToEdit::setEmail);
        Optional.ofNullable(memberRequest.getPassword()).ifPresent(memberToEdit::setPassword);
        Optional.ofNullable(memberRequest.getFirstName()).ifPresent(memberToEdit::setFirstName);
        Optional.ofNullable(memberRequest.getLastName()).ifPresent(memberToEdit::setLastName);
        Optional.ofNullable(memberRequest.getStreet()).ifPresent(memberToEdit::setStreet);
        Optional.ofNullable(memberRequest.getZip()).ifPresent(memberToEdit::setZip);
        Optional.ofNullable(memberRequest.getCity()).ifPresent(memberToEdit::setCity);
        memberRepository.save(memberToEdit);

    }
}
