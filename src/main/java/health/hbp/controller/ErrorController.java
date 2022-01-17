package health.hbp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    static HashMap<Integer, String> errorPages = new HashMap<>();

    static {
        errorPages.put(HttpStatus.FORBIDDEN.value(), "error-403");
        errorPages.put(HttpStatus.NOT_FOUND.value(), "error-404");
        errorPages.put(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error-500");
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null && errorPages.get(Integer.valueOf(status.toString())) != null) {
            return errorPages.get(Integer.valueOf(status.toString()));
        }
        return "error";
    }
}
