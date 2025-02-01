package study.spring.mvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {

    /**
     * 모든 HTTP 메서드 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("hello basic");
        return "ok";
    }

    /**
     * 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String processGetMappingV1() {
        log.info("mapping get v1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션
     * @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping
     * 애노테이션 내부에서 @RequestMapping에 HTTP 메서드를 지정해 사용
     */
    @GetMapping("/mapping-get-v2")
    public String processGetMappingV2() {
        log.info("mapping get v2");
        return "ok";
    }

    /**
     * @PathVariable
     * 변수 이름과 동일한 경우 애노테이션의 name 생략 가능
     */
    @GetMapping("/mapping/{userId}")
    public String processMappingPathVariable(@PathVariable("userId") String userId) {
        log.info("mapping path variable: userId = {}", userId);
        return "ok";
    }

    /**
     * 다중 @PathVariable
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String processMappingMultiplePathVariables(
            @PathVariable("userId") String userId,
            @PathVariable("orderId") Long orderId
    ) {
        log.info("mapping multiple path variables: userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String processMappingParameter() {
        log.info("mapping parameter");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String processMappingHeader() {
        log.info("mapping header");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 Media Type 추가 매핑
     * consumes = "application/json"
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String processMappingConsumes() {
        log.info("mapping consumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type 추가 매핑
     * produces = "text/html"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String processMappingProduces() {
        log.info("mapping produces");
        return "ok";
    }

}