package study.spring.mvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void getRequestParamV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("name = {}, age = {}", name, age);

        response.getWriter().write("ok");
    }

}