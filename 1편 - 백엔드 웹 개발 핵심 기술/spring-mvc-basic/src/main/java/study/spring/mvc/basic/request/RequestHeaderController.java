package study.spring.mvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String getHeaderInfo(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value = "Custom-Cookie", required = false) String cookie
            ) {
        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("http method = {}", httpMethod);
        log.info("locale = {}", locale);
        log.info("header map = {}", headerMap);
        log.info("header host = {}", host);
        log.info("custom cookie = {}", cookie);

        return "ok";
    }

}