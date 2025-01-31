package study.servlet.web.springmvc.v3;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.servlet.domain.member.Member;
import study.servlet.domain.member.MemberRepository;

/**
 * Model 도입
 * viewName 반환
 * @RequestParam 사용
 * @RequestMapping을 @GetMapping, @PostMapping으로 변경
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String showNewForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String saveMember(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            Model model
    ) {
        Member member = new Member(name, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }

    @GetMapping
    public String findAllMembers(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }

}