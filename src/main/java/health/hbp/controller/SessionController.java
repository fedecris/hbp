package health.hbp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController {

    @GetMapping("/session/check")
    public ResponseEntity<Integer> count(HttpSession session) {
        Integer counter = (Integer)session.getAttribute("count");
        session.setAttribute("count", (counter==null?1:counter+1));
        return ResponseEntity.ok((counter==null?1:counter+1));
    }

}
