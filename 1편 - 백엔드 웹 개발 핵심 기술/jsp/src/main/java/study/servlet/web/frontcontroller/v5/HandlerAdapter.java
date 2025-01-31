package study.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import study.servlet.web.frontcontroller.ModelView;

public interface HandlerAdapter {

    boolean canSupport(Object handler);
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException;

}