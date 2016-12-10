package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trainSimulator.services.EventLogService;
import trainSimulator.services.RunSimulationService;
import trainSimulator.services.TimetableGeneratorService;
import trainSimulator.services.TrainService;

import java.io.IOException;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
@RequestMapping("/adminPanel")
public class AdminPanelController {
    private final TimetableGeneratorService timetableGeneratorService;
    private final EventLogService eventLogService;
    private final RunSimulationService runSimulationService;
    private final TrainService trainService;

    @Autowired
    public AdminPanelController(TimetableGeneratorService timetableGeneratorService, EventLogService eventLogService,
                                RunSimulationService runSimulationService, TrainService trainService) {
        this.timetableGeneratorService = timetableGeneratorService;
        this.runSimulationService = runSimulationService;
        this.eventLogService = eventLogService;
        this.trainService = trainService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String generatorView() {
        return "adminPanel";
    }

    @ResponseBody
    public String generatorUtilities(ModelMap modelMap) throws IOException {
        modelMap.addAttribute("timetableGeneratorService", timetableGeneratorService);
        modelMap.addAttribute("trainService", trainService);
        return "timetable";
    }

    @ResponseBody
    public String eventLogsUtilities(ModelMap modelMap) throws IOException {
        modelMap.addAttribute("eventLogService", eventLogService);
        return "adminPanel";
    }

    @ResponseBody
    public String simulationUtilities(ModelMap modelMap) throws IOException {
        modelMap.addAttribute("runSimulationService", runSimulationService);
        return "timetable";
    }

}
