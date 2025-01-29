package study.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import study.servlet.domain.member.Member;
import study.servlet.domain.member.MemberRepository;

/**
 *  1. 파라미터 조회 후 Member 객체 생성
 *  2. MemberRepository.save()를 통해 Member 객체 저장
 *  3. Member 객체를 사용해 결과 화면 HTML을 동적으로 생성 후 응답
 */
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age")); // getParameter 메서드는 항상 문자열을 반환

        Member member = new Member(name, age);
        memberRepository.save(member);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.write("<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "성공\n" +
                        "<ul>\n" +
                        "    <li>id="+member.getId()+"</li>\n" +
                        "    <li>name="+member.getName()+"</li>\n" +
                        "    <li>age="+member.getAge()+"</li>\n" +
                        "</ul>\n" +
                        "<a href=\"/index.html\">메인</a>\n" +
                        "</body>\n" +
                        "</html>");
    }

}