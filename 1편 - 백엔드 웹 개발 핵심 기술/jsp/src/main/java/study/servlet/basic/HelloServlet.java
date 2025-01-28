package study.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request); // HttpServletRequest 인터페이스의 구현체 출력
        System.out.println("response = " + response); // HttpServletResponse 인터페이스의 구현체 출력

        String name = request.getParameter("name"); // 쿼리 파라미터 값 조회
        System.out.println("name = " + name);

        response.setContentType("text/plain"); // 응답 메시지 헤더
        response.setCharacterEncoding("utf-8"); // 응답 메시지 헤더
        response.getWriter().write("Hello " + name); // 응답 메시지 바디
    }

}
