package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainSimulator.services.TrainService;

/**
 * Created by mitron-wojtek on 19.11.16.
 */
@Controller
@RequestMapping("/timetable")
public class TimetableGeneratorController {
    private final TrainService trainService;

    @Autowired
    public TimetableGeneratorController(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listTrains(ModelMap modelMap) {
        modelMap.addAttribute("trainsList", trainService.getAllTrains());
        return "trains";
    }
}
