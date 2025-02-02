package study.spring.mvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 요청 파라미터와 다르게, HTTP 요청 메시지 바디를 통해 데이터가 전달되는 경우
 * @RequestParam, @ModelAttribute로 데이터 조회 불가
 */
@Controller
@Slf4j
public class RequestBodyStringController {

    /**
     * HttpServletRequest, HttpServletResponse
     */
    @PostMapping("/request-body-string-v1")
    public void getRequestBodyStringV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("message body = {}", messageBody);
        response.getWriter().write("ok");
    }

    /**
     * InputStream, Writer
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용 조회
     * OutputStream(Writer): HTTP 응답 메시지 바디에 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void getRequestBodyStringV2(
            InputStream inputStream,
            Writer responseWriter
    ) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("message body = {}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity
     * HTTP 요청 메시지 헤더, 바디 정보를 편리하게 조회
     * 응답으로도 사용 가능
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> getRequestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("message body = {}", messageBody);
        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody, @ResponseBody
     */
    @PostMapping("/request-body-string-v4")
    @ResponseBody
    public String getRequestBodyStringV4(@RequestBody String messageBody) {
        log.info("message body = {}", messageBody);
        return "ok";
    }

}