package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trainSimulator.services.TimetableGeneratorService;
import trainSimulator.services.TrainService;
import trainSimulator.services.EventLogService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
@RequestMapping("/adminPanel")
public class AdminPanelController {
    private final TimetableGeneratorService timetableGeneratorService;
    private final TrainService trainService;
    private final EventLogService eventLogService;

    @Autowired
    public AdminPanelController(TimetableGeneratorService timetableGeneratorService, TrainService trainService, EventLogService eventLogService) {
        this.timetableGeneratorService = timetableGeneratorService;
        this.trainService = trainService;
        this.eventLogService = eventLogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String generatorView() {
        return "generator";
    }

    @RequestMapping("/generate")
    @ResponseBody
    public void generateTimetable(HttpServletResponse httpServletResponse) throws IOException {
        timetableGeneratorService.generateTimetable();
        httpServletResponse.sendRedirect("/timetable");
    }

    @RequestMapping("/clearTimetable")
    @ResponseBody
    public void clearTimetable(HttpServletResponse httpServletResponse) throws IOException {
        trainService.clearTrains();
        httpServletResponse.sendRedirect("/timetable");
    }

    @RequestMapping("/clearLogs")
    @ResponseBody
    public void clearLogs(HttpServletResponse httpServletResponse) throws IOException {
        eventLogService.clearEvents();
        httpServletResponse.sendRedirect("/adminPanel");
    }

}
