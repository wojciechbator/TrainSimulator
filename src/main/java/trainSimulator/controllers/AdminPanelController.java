package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainSimulator.services.EventLogService;
import trainSimulator.services.RunSimulationService;
import trainSimulator.services.TimetableGeneratorService;
import trainSimulator.services.TrainService;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
public class AdminPanelController {
    private final TimetableGeneratorService timetableGeneratorService;
    private final TrainService trainService;
    private final EventLogService eventLogService;
    private final RunSimulationService runSimulationService;

    @Autowired
    public AdminPanelController(TimetableGeneratorService timetableGeneratorService, TrainService trainService,
                                EventLogService eventLogService, RunSimulationService runSimulationService) {
        this.timetableGeneratorService = timetableGeneratorService;
        this.trainService = trainService;
        this.eventLogService = eventLogService;
        this.runSimulationService = runSimulationService;
    }

    @RequestMapping(value="/adminPanel", method = RequestMethod.GET)
    public String generatorView() {
        return "adminPanel";
    }

    @RequestMapping(value="/adminPanel", params={"createTrains"})
    public String createTrains(final TimetableGeneratorService timetableGeneratorService, final BindingResult bindingResult) {
        timetableGeneratorService.generateTimetable();
        return "adminPanel";
    }

    @RequestMapping(value="/adminPanel", params={"clearTrains"})
    public String clearTrains() {
        trainService.clearTrains();
        return "adminPanel";
    }

    @RequestMapping(value="/adminPanel", params={"clearLogs"})
    public String clearLogs() {
        eventLogService.clearEvents();
        return "adminPanel";
    }

    @RequestMapping(value="/adminPanel", params={"runSimulation"})
    public String runSimulation() {
        runSimulationService.runSimulation();
        return "adminPanel";
    }

    @RequestMapping(value="/adminPanel", params={"stopSimulation"})
    public String stopSimulation() {
        runSimulationService.stopSimulation();
        return "adminPanel";
    }
}
