package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainSimulator.models.Ticket;
import trainSimulator.services.TicketService;
import trainSimulator.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by wojciech on 29.12.16.
 */
@Controller
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;

    @ModelAttribute("ticket")
    public Ticket ticket() {
        return new Ticket();
    }

    @Autowired
    public UserController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @RequestMapping("/account")
    public String account(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("user", userService.findOneByName(name));
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String buyTicket(Model model, @Valid @ModelAttribute("ticket") Ticket ticket,
                            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return account(model, principal);
        }
        String name = principal.getName();
        ticketService.createTicket(ticket, name);
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/remove/{id}")
    public String cancelTicket(@PathVariable long id) {
        ticketService.deleteTicketById(id);
        return "redirect:/account";
    }

}
