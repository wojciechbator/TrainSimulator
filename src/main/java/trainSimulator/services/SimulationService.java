package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import trainSimulator.models.*;
import trainSimulator.utilities.TrainState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
public class SimulationService implements Runnable {
    private final TrainService trainService;
    private final GeneratorParametersService generatorParametersService;
    private final EventLogService eventLogService;
    private static Logger logger = Logger.getLogger(SimulationService.class);
    private final Object mutexObject = new Object();
    private boolean isRunning = true;

    public SimulationService(TrainService trainService, GeneratorParametersService generatorParametersService,
                             EventLogService eventLogService) {
        this.trainService = trainService;
        this.generatorParametersService = generatorParametersService;
        this.eventLogService = eventLogService;
    }

    @Override
    public void run() {
        logger.info("Running simulation instance.");
        boolean runFlag = true;
        List<Train> allTrains = trainService.getAllTrains();
        List<Train> nearestTrainsOnThisStation = getNearestTrains(allTrains);
        while (runFlag) {
            synchronized (mutexObject) {
                runFlag = isRunning;
            }
            if (nearestTrainsOnThisStation != null) {
                for (Train train : nearestTrainsOnThisStation) {
                    if (train.getState() != TrainState.CANCELLED) {
                        stateMachine(train);
                    }
                }
            }
        }
    }

    private void stateMachine(Train train) {
        Date now = new Date();
        int differenceArrival = (int) ((train.getTimetable().get(0).getArrivalTime().getTime() - now.getTime()) / 1000);
        if (train.getState() != TrainState.CANCELLED) {
            if (differenceArrival <= Integer.valueOf(generatorParametersService.findGeneratorParameterById(5).getParameterValue()) &&
                    differenceArrival > Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())) {
                if (train.getState() != TrainState.ARRIVING) {
                    train.setState(TrainState.ARRIVING);
                    String logText = "Train with id: " + train.getId() + " is approaching on station " + train.getStation().getName();
                    logger.info(logText);
                    eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), logText));
                }
            } else if (differenceArrival <= Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())
                    && differenceArrival >= -Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())) {
                if (train.getState() != TrainState.ONSTATION) {
                    train.setState(TrainState.ONSTATION);
                    String logText = "Train with id: " + train.getId() + " is on station or very close to " + train.getStation().getName();
                    logger.info(logText);
                    eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), logText));
                }
            } else if (differenceArrival < -Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())) {
                if (train.getState() != TrainState.DEPARTED) {
                    train.setState(TrainState.DEPARTED);
                    String logText = "Train with id: " + train.getId() + " departed from station " + train.getStation().getName();
                    logger.info(logText);
                    eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), logText));
                }
            }
        }
    }

    private List<Train> getNearestTrains(List<Train> allTrains) {
        List<Train> nearestTrains = new ArrayList<>();
        for (Train train : allTrains) {
            for (TimetableEntity timetableEntity : train.getTimetable()) {
                if (timetableEntity.getArrivalTime().getTime() < DateUtils.addMinutes(new Date(),
                        Integer.valueOf(generatorParametersService.findGeneratorParameterById(7).getParameterValue())).getTime()
                        && train.getState() != TrainState.DEPARTED) {
                    nearestTrains.add(train);
                }
            }
        }
        logger.info("Got nearest trains for simulation.");
        return nearestTrains;
    }
}
