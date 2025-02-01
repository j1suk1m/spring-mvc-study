package study.spring.mvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class RequestParamController {

    /**
     * HttpServletRequest.getParameter() 조회
     */
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

    /**
     * @RequestParam(name) 조회
     * name으로 바인딩
     */
    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String getRequestParamV2(
            @RequestParam("name") String userName,
            @RequestParam("age") int userAge
    ) {
        log.info("name = {}, age = {}", userName, userAge);
        return "ok";
    }

    /**
     * @RequestParam 조회
     * name이 변수 이름과 동일한 경우 name 생략 가능
     */
    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String getRequestParamV3(
            @RequestParam String name,
            @RequestParam int age
    ) {
        log.info("name = {}, age = {}", name, age);
        return "ok";
    }

    /**
     * @RequestParam 생략
     * 변수가 String, int, Integer 등의 단순 타입인 경우 @RequestParam 생략 가능
     */
    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String getRequestParamV4(
            String name,
            int age
    ) {
        log.info("name = {}, age = {}", name, age);
        return "ok";
    }

    /**
     * 파라미터 필수 여부
     * 디폴트는 true
     */
    @RequestMapping("/request-param-required")
    @ResponseBody
    public String getRequestParamRequired(
            @RequestParam(required = true) String name,
            @RequestParam(required = false) Integer age
    ) {
        log.info("name = {}, age = {}", name, age);
        return "ok";
    }

    /**
     * 디폴트 값 사용
     * 빈 문자의 경우에도 디폴트 값 적용
     */
    @RequestMapping("/request-param-default")
    @ResponseBody
    public String getRequestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String name,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("name = {}, age = {}", name, age);
        return "ok";
    }

    /**
     * 파라미터 맵 조회
     */
    @RequestMapping("/request-param-map")
    @ResponseBody
    public String getRequestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("name = {}, age = {}", paramMap.get("name"), paramMap.get("age"));
        return "ok";
    }

}