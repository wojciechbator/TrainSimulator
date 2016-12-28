package trainSimulator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wojciech on 28.12.16.
 */
@Controller
public class PageNotFoundController {
    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public String notFoundRender() {
        return "customNotFoundPage";
    }

    //Może kiedyś użyjemy do obsługi różnego rodzaju błędów (nie tylko 404), do tego przyda się status
    private int getHttpStatusCOde(final HttpServletRequest request) {
        return (int) request.getAttribute("javax.servlet.error.status_code");
    }
}
