package study.servlet.web.springmvc.v2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import study.servlet.domain.member.Member;
import study.servlet.domain.member.MemberRepository;

/**
 * 클래스 단위에서 메서드 단위로 변경
 * @RequestMapping의 클래스 레벨과 메서드 레벨 조합을 통한 중복 제거
 */
@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView showNewForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView saveMember(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(name, age);
        memberRepository.save(member);

        ModelAndView modelAndView = new ModelAndView("save-result");
        modelAndView.addObject("member", member);

        return modelAndView;
    }

    @RequestMapping
    public ModelAndView findAllMembers() {
        List<Member> members = memberRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("members");
        modelAndView.addObject("members", members);

        return modelAndView;
    }

}