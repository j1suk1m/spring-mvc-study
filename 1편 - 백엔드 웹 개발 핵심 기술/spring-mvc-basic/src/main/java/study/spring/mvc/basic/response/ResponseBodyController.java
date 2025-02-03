package study.spring.mvc.basic.response;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.spring.mvc.basic.HelloData;

// @RestController // REST API를 위해 사용하는 컨트롤러로, 전체 메서드에 @ResponseBody 적용
@Controller
@Slf4j
public class ResponseBodyController {

    /**
     * HttpServletResponse 객체를 통해 HTTP 메시지 바디에 응답 데이터 전달
     * @param response
     */
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity: HTTP 메시지의 헤더, 바디 정보 가짐
     * ResponseEntity: HttpEntity를 상속받음 + HTTP 응답 코드 설정 가능
     * @return ResponseEntity<String>
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @ResponseBody: 뷰를 사용하지 않고 HTTP 메시지 컨버터를 통해 HTTP 메시지 입력
     * @return String
     */
    @GetMapping("/response-body-string-v3")
    @ResponseBody
    public String responseBodyV3() {
        return "ok";
    }

    /**
     * ResponseEntity: HTTP 메시지 컨버터를 통해 JSON 형식으로 변환 후 반환
     * @return ResponseEntity<HelloData>
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData data = new HelloData();
        data.setName("userA");
        data.setAge(20);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @ResponseStatus: @ResponseBody 사용 시 응답 코드 설정
     * 응답 코드의 동적인 변경을 위해서는 ResponseEntity 필요
     * @return HelloData
     */
    @GetMapping("/response-body-json-v2")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public HelloData responseBodyJsonV2() {
        HelloData data = new HelloData();
        data.setName("userA");
        data.setAge(20);
        return data;
    }

}