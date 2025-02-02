package study.spring.mvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import study.spring.mvc.basic.HelloData;

@Controller
@Slf4j
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * HttpServletRequest, HttpServletResponse
     */
    @PostMapping("/request-body-json-v1")
    public void getRequestBodyJsonV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("message body = {}", messageBody);

        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("name = {}, age = {}", data.getName(), data.getAge());

        response.getWriter().write("ok");
    }

    /**
     * @RequestBody, @ResponseBody
     */
    @PostMapping("/request-body-json-v2")
    @ResponseBody
    public String getRequestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("message body = {}", messageBody);

        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("name = {}, age = {}", data.getName(), data.getAge());

        return "ok";
    }

    /**
     * @RequestBody, @ResponseBody
     * @RequestBody 생략 불가, 생략할 경우 @ModelAttribute가 적용되어 요청 파라미터 처리 시도
     */
    @PostMapping("/request-body-json-v3")
    @ResponseBody
    public String getRequestBodyJsonV3(@RequestBody HelloData data) {
        log.info("name = {}, age = {}", data.getName(), data.getAge());
        return "ok";
    }

    /**
     * HttpEntity
     */
    @PostMapping("/request-body-json-v4")
    @ResponseBody
    public String getRequestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData data = httpEntity.getBody();
        log.info("name = {}, age = {}", data.getName(), data.getAge());
        return "ok";
    }

    /**
     * @RequestBody, @ResponseBody
     * 객체 반환 시 JSON으로 변환
     */
    @PostMapping("/request-body-json-v5")
    @ResponseBody
    public HelloData getRequestBodyJsonV5(@RequestBody HelloData data) {
        log.info("name = {}, age = {}", data.getName(), data.getAge());
        return data;
    }

}