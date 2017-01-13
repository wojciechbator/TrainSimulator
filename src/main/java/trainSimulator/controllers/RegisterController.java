package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import trainSimulator.models.EventLog;
import trainSimulator.models.User;
import trainSimulator.services.EventLogService;
import trainSimulator.services.UserService;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by wojciech on 29.12.16.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private final EventLogService eventLogService;

    @Autowired
    public RegisterController(UserService userService, EventLogService eventLogService) {
        this.userService = userService;
        this.eventLogService = eventLogService;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @RequestMapping
    public String showRegister() {
        return "userRegister";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userRegister";
        }
        userService.saveUser(user);
        EventLog eventLog = new EventLog("INFO", "", new Date(), "User" + user.getName() + " is registered!");
        eventLogService.saveEvent(eventLog);
        return "redirect:/register?success=true";
    }

    @RequestMapping("/available")
    public String available(@RequestParam String username) {
        Boolean available = userService.findOneByName(username) == null;
        return available.toString();
    }
}
