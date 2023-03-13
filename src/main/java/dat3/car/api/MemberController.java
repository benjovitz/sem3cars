package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import dat3.car.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/members/")
class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService){
       this.memberService=memberService;
    }

    //Admin only
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMembers(false);
    }

    //Admin
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.findMemberByUsername(username);
    }

    //Anonymous
    @PostMapping
    MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    //Member
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        memberService.editMember(username,body);
        return ResponseEntity.ok(true);
    }

    //Admin
    @PatchMapping("/ranking/{username}/{value}")
    MemberResponse setRankingForUser(@PathVariable String username, @PathVariable int value) {
       return memberService.setRankingForUser(username,value);
    }

    //Admin
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {
    memberService.deleteMember(username);
    }
}

