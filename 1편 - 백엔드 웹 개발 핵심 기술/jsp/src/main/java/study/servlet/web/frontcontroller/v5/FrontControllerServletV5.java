package study.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import study.servlet.web.frontcontroller.ModelView;
import study.servlet.web.frontcontroller.View;
import study.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import study.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import study.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import study.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // key == 매핑 URL, value == 호출될 핸들러
    private final Map<String, Object> handlerMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        ModelView modelView = handlerAdapter.handle(request, response, handler);

        View view = resolve(modelView.getViewName());
        view.render(modelView.getModel(), request, response);
    }

    private void initHandlerMap() {
        handlerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMap.get(requestURI);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        return handlerAdapters.stream()
                              .filter(adapter -> adapter.canSupport(handler))
                              .findFirst()
                              .orElseThrow(() ->
                                      new IllegalArgumentException(handler + "에 대한 어댑터를 찾을 수 없습니다."));
    }

    private View resolve(String viewName) {
        return new View("/WEB-INF/views/" + viewName + ".jsp");
    }

}