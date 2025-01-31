package study.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import study.servlet.web.frontcontroller.View;

public interface ControllerV2 {

    View process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}