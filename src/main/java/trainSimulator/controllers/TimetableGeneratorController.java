package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trainSimulator.services.TimetableGeneratorService;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
@RequestMapping("/generator")
public class TimetableGeneratorController {
    private final TimetableGeneratorService timetableGeneratorService;

    @Autowired
    public TimetableGeneratorController(TimetableGeneratorService timetableGeneratorService) {
        this.timetableGeneratorService = timetableGeneratorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public void generateTimetable() {
        timetableGeneratorService.generateTimetable();
    }

}
