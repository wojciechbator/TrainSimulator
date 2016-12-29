package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import trainSimulator.services.UserService;

import java.security.Principal;

/**
 * Created by wojciech on 29.12.16.
 */
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/account")
    public String account(ModelMap modelMap, Principal principal) {
        String name = principal.getName();
        modelMap.addAttribute("user", userService.findOneByName(name));
        return "account";
    }


}
