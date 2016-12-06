package trainSimulator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trainSimulator.models.Station;
import trainSimulator.services.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by mitron-wojtek on 27.11.16.
 */
@Controller
@RequestMapping("/adminPanel")
public class AdminPanelController {
    private final TimetableGeneratorService timetableGeneratorService;
    private final TrainService trainService;
    private final EventLogService eventLogService;
    private final StationService stationService;
    private final GeneratorParametersService generatorParametersService;
    private ExecutorService executorService;

    @Autowired
    public AdminPanelController(TimetableGeneratorService timetableGeneratorService, TrainService trainService,
                                EventLogService eventLogService, StationService stationService, GeneratorParametersService generatorParametersService) {
        this.timetableGeneratorService = timetableGeneratorService;
        this.trainService = trainService;
        this.eventLogService = eventLogService;
        this.stationService = stationService;
        this.generatorParametersService = generatorParametersService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String generatorView() {
        return "adminPanel";
    }

    @RequestMapping("/generate")
    @ResponseBody
    public void generateTimetable(HttpServletResponse httpServletResponse) throws IOException {
        timetableGeneratorService.generateTimetable();
        //TODO: Do I really need this idiotic redirection to self page? Gonna fix it asap
        httpServletResponse.sendRedirect("/adminPanel");
    }

    @RequestMapping("/clearTimetable")
    @ResponseBody
    public void clearTimetable(HttpServletResponse httpServletResponse) throws IOException {
        trainService.clearTrains();
        httpServletResponse.sendRedirect("/adminPanel");
    }

    @RequestMapping("/clearLogs")
    @ResponseBody
    public void clearLogs(HttpServletResponse httpServletResponse) throws IOException {
        eventLogService.clearEvents();
        httpServletResponse.sendRedirect("/adminPanel");
    }

    @RequestMapping("/runSimulation")
    @ResponseBody
    public void runSimulation(HttpServletResponse httpServletResponse) throws IOException {
        executorService = Executors.newFixedThreadPool(stationService.findAllStations().size());
        List<Station> allStations = stationService.findAllStations();
        for (Station station : allStations) {
            if (station.getTrainsOnStation().size() > 0) {
                Runnable simulationWorker = new SimulationService(trainService, generatorParametersService,
                        stationService, eventLogService, station);
                executorService.execute(simulationWorker);
            }
        }

        httpServletResponse.sendRedirect("/adminPanel");
    }

    @RequestMapping("/stopSimulation")
    @ResponseBody
    public void stopSimulation(HttpServletResponse httpServletResponse) throws IOException {
        //kill threads in elegant way
        if (!executorService.isShutdown()) {
            executorService.shutdown();
            try {
                executorService.awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        httpServletResponse.sendRedirect("/adminPanel");
    }

}
