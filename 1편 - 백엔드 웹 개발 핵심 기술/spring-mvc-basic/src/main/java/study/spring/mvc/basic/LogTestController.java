package study.spring.mvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    @GetMapping("/log-test")
    public String testLog() {
        String word = "spring mvc";

        log.trace("trace log = {}", word);
        log.debug("debug log = {}", word);
        log.info("info log = {}", word);
        log.warn("warn log = {}", word);
        log.error("error log = {}", word);

        return "ok";
    }

}