package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainSimulator.models.Train;
import trainSimulator.services.TrainService;

import java.util.List;

/**
 * Created by mitron-wojtek on 19.11.16.
 */
@Controller
@RequestMapping("/timetable")
public class TimetableController {
    private final TrainService trainService;

    @ModelAttribute("allTrains")
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @Autowired
    public TimetableController(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getTimetable(ModelMap modelMap) {
        modelMap.addAttribute("trainsList", trainService.getAllTrains());
        return "timetable";
    }
}
