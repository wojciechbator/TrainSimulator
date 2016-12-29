package trainSimulator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wojciech on 29.12.16.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
