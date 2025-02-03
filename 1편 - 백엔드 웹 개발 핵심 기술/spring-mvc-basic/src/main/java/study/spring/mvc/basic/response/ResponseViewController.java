package study.spring.mvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    /**
     * @return modelAndView
     */
    @GetMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView modelAndView = new ModelAndView("response/hello");
        modelAndView.addObject("data", "hello");
        return modelAndView;
    }

    /**
     * 권장하는 방법
     * 뷰 리졸버 실행 -> 뷰 조회 -> 렌더링
     * @param model
     * @return viewName
     */
    @GetMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello");
        return "response/hello";
    }

    /**
     * 권장하지 않는 방법
     * @Controller 사용 + HTTP 메시지 바디를 처리하는 파라미터 X -> URL을 뷰의 논리 이름으로 사용
     * @param model
     */
    @GetMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello");
    }

}