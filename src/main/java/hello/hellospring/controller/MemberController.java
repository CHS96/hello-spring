package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //    @Autowired private MemberService memberService; 의존성 : 필드 주입 -> 중간에 바꿀 방법이 없음
    private final MemberService memberService;

    //의존성 : setter 주입 -> public 접근 제한자라 노출되서 변경을 통해 오류 생길 수 있음
//  public void setMemberService(MemberService memberService) {
//      this.memberService = memberService;
//  }

    //의존성 : 생성자 주입 - 권장
    @Autowired //스프링에서 관리하는 memberService 객체를 연결시켜줌 => dependency injection (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
