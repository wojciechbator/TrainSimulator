package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainSimulator.services.EventLogService;
import trainSimulator.services.RunSimulationService;
import trainSimulator.services.TimetableGeneratorService;
import trainSimulator.services.UserService;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
@RequestMapping(value = "/adminPanel")
public class AdminPanelController {
    private final TimetableGeneratorService timetableGeneratorService;
    private final EventLogService eventLogService;
    private final RunSimulationService runSimulationService;
    private final UserService userService;

    @Autowired
    public AdminPanelController(TimetableGeneratorService timetableGeneratorService, EventLogService eventLogService,
                                RunSimulationService runSimulationService, UserService userService) {
        this.timetableGeneratorService = timetableGeneratorService;
        this.eventLogService = eventLogService;
        this.runSimulationService = runSimulationService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String generatorView() {
        return "adminPanel";
    }

    @RequestMapping(value="/createTrains", method = RequestMethod.POST)
    public String createTrains() {
        timetableGeneratorService.generateTimetable();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/clearTrains", method = RequestMethod.POST)
    public String clearTrains() {
        timetableGeneratorService.clearTimetable();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/clearLogs", method = RequestMethod.POST)
    public String clearLogs() {
        eventLogService.clearEvents();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/runSimulation", method = RequestMethod.POST)
    public String runSimulation() {
        runSimulationService.runSimulation();
        return "redirect:/adminPanel";
    }

    @RequestMapping(value="/stopSimulation", method = RequestMethod.POST)
    public String stopSimulation() {
        runSimulationService.stopSimulation();
        return "redirect:/adminPanel";
    }

    @RequestMapping
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "adminPanel";
    }

    @RequestMapping("/{id}")
    public String userDetails(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.findOne(id));
        return "userDetail";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/adminPanel";
    }

}
