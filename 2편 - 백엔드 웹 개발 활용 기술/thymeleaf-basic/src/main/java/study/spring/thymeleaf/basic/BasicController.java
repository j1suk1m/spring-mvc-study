package study.spring.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String renderBasicTextView(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String renderUnescapedTextView(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String renderVariableView(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> users = new ArrayList<>(List.of(userA, userB));

        Map<String, User> userMap = new HashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", users);
        model.addAttribute("userMap", userMap);

        return "basic/variable";
    }

    @GetMapping("basic-objects")
    public String renderBasicObjectsView(
            Model model,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session
    ) {
        session.setAttribute("sessionData", "Hello Session");

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());

        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String renderDateView(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String renderLinkView(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");

        return "basic/link";
    }

    @GetMapping("/literal")
    public String renderLiteralView(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String renderOperationView(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");

        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String renderAttributeView(Model model) {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String renderEachView(Model model) {
        addUsers(model);
        return "basic/each";
    }

    @GetMapping("/condition")
    public String renderConditionView(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String renderCommentsView(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    private void addUsers(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);
        User userC = new User("userC", 30);

        List<User> users = new ArrayList<>(List.of(userA, userB, userC));

        model.addAttribute("users", users);
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String username;
        private int age;
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

}