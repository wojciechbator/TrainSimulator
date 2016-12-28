package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainSimulator.services.EventLogService;
import trainSimulator.services.RunSimulationService;
import trainSimulator.services.TimetableGeneratorService;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
public class AdminPanelController {
    private final TimetableGeneratorService timetableGeneratorService;
    private final EventLogService eventLogService;
    private final RunSimulationService runSimulationService;

    @Autowired
    public AdminPanelController(TimetableGeneratorService timetableGeneratorService, EventLogService eventLogService,
                                RunSimulationService runSimulationService) {
        this.timetableGeneratorService = timetableGeneratorService;
        this.eventLogService = eventLogService;
        this.runSimulationService = runSimulationService;
    }

    @RequestMapping(value="/adminPanel", method = RequestMethod.GET)
    public String generatorView() {
        return "adminPanel";
    }

    @RequestMapping(value="/adminPanel/createTrains", method = RequestMethod.POST)
    public String createTrains() {
        timetableGeneratorService.generateTimetable();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/adminPanel/clearTrains", method = RequestMethod.POST)
    public String clearTrains() {
        timetableGeneratorService.clearTimetable();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/adminPanel/clearLogs", method = RequestMethod.POST)
    public String clearLogs() {
        eventLogService.clearEvents();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/adminPanel/runSimulation", method = RequestMethod.POST)
    public String runSimulation() {
        runSimulationService.runSimulation();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/adminPanel/stopSimulation", method = RequestMethod.POST)
    public String stopSimulation() {
        runSimulationService.stopSimulation();
        return "redirect:/adminPanel";
    }
}
