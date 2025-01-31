package study.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printTotalParameters(request);
        printSingleParameter(request);
        printDuplicateParameters(request);
    }

    private void printDuplicateParameters(HttpServletRequest request) {
        System.out.println("--- DUPLICATE PARAMETERS START ---");

        String[] names = request.getParameterValues("name");

        for (String name : names) {
            System.out.println("name = " + name);
        }

        System.out.println("--- DUPLICATE PARAMETERS END ---");
        System.out.println();
    }

    private void printSingleParameter(HttpServletRequest request) {
        System.out.println("--- SINGLE PARAMETER START ---");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        System.out.println("--- SINGLE PARAMETER END ---");
        System.out.println();
    }

    private void printTotalParameters(HttpServletRequest request) {
        System.out.println("--- TOTAL PARAMETERS START ---");

        /*
        parameterName == key
        request.getParameter(parameterName) == value
         */
        request.getParameterNames().asIterator()
                .forEachRemaining(parameterName
                        -> System.out.println(parameterName + " = " + request.getParameter(parameterName)));

        System.out.println("--- TOTAL PARAMETERS END ---");
        System.out.println();
    }

}
