package study.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeader(request);
        printHeaderUtil(request);
        printExtras(request);
    }

    private void printExtras(HttpServletRequest request) {
        System.out.println("--- EXTRAS START ---");

        System.out.println("[Remote]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());

        System.out.println("--- EXTRAS END ---");
        System.out.println();
    }

    private void printHeaderUtil(HttpServletRequest request) {
        System.out.println("--- HEADER UTIL START ---");

        System.out.println("[Host]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language]");

        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));

        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[Cookie]");

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }

        System.out.println();

        System.out.println("[Content]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- HEADER UTIL END ---");
        System.out.println();
    }

    private void printHeader(HttpServletRequest request) {
        System.out.println("--- HEADER START ---");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        System.out.println(headerName + ": " + request.getHeader(headerName)));

        System.out.println("--- HEADER END ---");
        System.out.println();
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST LINE START ---");

        System.out.println("request.getMethod() = " + request.getMethod()); // HTTP 메서드 // GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); // 전체 프로토콜 및 버전 // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); // 스키마 // http
        System.out.println("request.getRequestURL() = " + request.getRequestURL()); // 요청 URL // http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI()); // 요청 URI // /request-header
        System.out.println("request.getQueryString() = " + request.getQueryString()); // 쿼리 파라미터
        System.out.println("request.isSecure() = " + request.isSecure()); // HTTPS 사용 유무 // false

        System.out.println("--- REQUEST LINE END ---");
        System.out.println();
    }

}
