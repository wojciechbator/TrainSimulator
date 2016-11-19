package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mitron-wojtek on 15.11.16.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String helloFromSpring(ModelMap model) {
        model.addAttribute("greeting", "CZESC, TUTAJ SPRING!");
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloAgain(ModelMap model) {
        model.addAttribute("greeting", "CZESC, TUTAJ TEZ SPRING!");
        return "index";
    }
}
