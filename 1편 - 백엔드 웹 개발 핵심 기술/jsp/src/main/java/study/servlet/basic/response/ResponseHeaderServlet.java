package study.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Status Line
        response.setStatus(HttpServletResponse.SC_OK);

        // Response Header
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 무효화
        response.setHeader("Pragma", "no-cache"); // 과거 버전의 캐시 삭제
        response.setHeader("Custom-Header", "hello"); // 커스텀 헤더

        // Response Header Utils
        setContentUtil(response);
        setCookieUtil(response);
        setRedirectionUtil(response);

        // Message Body
        PrintWriter writer = response.getWriter();
        writer.println("ok");

    }

    private void setRedirectionUtil(HttpServletResponse response) throws IOException {

        /*
        Status Code: 302
        Location: /basic/hello-form.html

        response.setStatus(HttpServletResponse.SC_FOUND); // 302
        response.setHeader("Location", "/basic/hello-form.html");
         */

        response.sendRedirect("/basic/hello-form.html");

    }

    private void setCookieUtil(HttpServletResponse response) {

        /*
        Set-Cookie: Custom-Cookie=good; Max-Age=600;

        response.setHeader("Set-Cookie", "Custom-Cookie=good; Max-Age=600");
         */

        Cookie cookie = new Cookie("Custom-Cookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);

    }

    private void setContentUtil(HttpServletResponse response) {
        /*
        Content-Type: text/plain;charset=utf-8
        Content-Length: 3

        response.setHeader("Content-Type", "text/plain;charset=utf-8");
         */

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setContentLength(3); // 생략 시 자동 생성

    }

}
