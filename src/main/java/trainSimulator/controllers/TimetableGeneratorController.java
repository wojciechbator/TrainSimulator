package trainSimulator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainSimulator.services.TimetableGeneratorService;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
@RequestMapping("/generator")
public class TimetableGeneratorController {
    private final TimetableGeneratorService timetableGeneratorService;

    public TimetableGeneratorController(TimetableGeneratorService timetableGeneratorService) {
        this.timetableGeneratorService = timetableGeneratorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String generateTimetable(ModelMap modelMap) {
     //   modelMap.addAttribute("timetableGeneratingButton", timetableGeneratorService.generateTimetable());
        return "timetable";
    }
}
