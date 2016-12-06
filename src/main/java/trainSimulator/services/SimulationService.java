package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import trainSimulator.models.*;

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
    //mutex object, for synchronizing threads
    private static Logger logger = Logger.getLogger(SimulationService.class);
    private final Object mutexObject = new Object();
    private boolean isRunning = true;
    private Station station;

    public SimulationService(TrainService trainService, GeneratorParametersService generatorParametersService,
                             EventLogService eventLogService, Station station) {
        this.trainService = trainService;
        this.generatorParametersService = generatorParametersService;
        this.eventLogService = eventLogService;
        this.station = station;
    }

    //Simulation will be in thread pool for every station, easy ExecutorService
    @Override
    public void run() {
        boolean runFlag = true;
        List<Train> allTrains;
        while(runFlag) {
            synchronized (mutexObject) {
                runFlag = isRunning;
            }
            allTrains = trainService.getAllTrains();
            List<Train> nearestTrainsOnThisStation = getNearestTrains(allTrains);
            if(nearestTrainsOnThisStation != null) {
                for(Train train : nearestTrainsOnThisStation) {
                    if(train.getState() != TrainState.CANCELLED) {
                        stateMachine(train);
                    }
                }
            }
        }
    }

    public void stateMachine(Train train) {
        Date now = new Date();
        int differenceArrival = (int) ((train.getTimetable().get(0).getArrivalTime().getTime() - now.getTime()) / 1000);
        if (train.getState() != TrainState.CANCELLED) {
            if (differenceArrival <= Integer.valueOf(generatorParametersService.findGeneratorParameterById(5).getParameterValue()) &&
                    differenceArrival > Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())) {
                if(train.getState() != TrainState.ARRIVING) {
                    train.setState(TrainState.ARRIVING);
                    String logText = "Train with id: " + train.getId() + " is approaching on station " + station.getName();
                    logger.info(logText);
                    eventLogService.createEvent(new EventLog("INFO", station.getName(), new Date(), logText));
                }
            }
            else if (differenceArrival <= Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())
                    && differenceArrival >= -Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())) {
                if(train.getState() != TrainState.ONSTATION) {
                    train.setState(TrainState.ONSTATION);
                    String logText = "Train with id: " + train.getId() + " is on station or very close to " + station.getName();
                    logger.info(logText);
                    eventLogService.createEvent(new EventLog("INFO", station.getName(), new Date(), logText));
                }
            }
            else if (differenceArrival < -Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())) {
                if(train.getState() != TrainState.DEPARTED) {
                    train.setState(TrainState.DEPARTED);
                    String logText = "Train with id: " + train.getId() + " departed from station " + station.getName();
                    logger.info(logText);
                    eventLogService.createEvent(new EventLog("INFO", station.getName(), new Date(), logText));
                }
            }
        }
    }

    public List<Train> getNearestTrains(List<Train> allTrains) {
        List<Train> nearestTrains = new ArrayList<>();
        for(Train train : allTrains) {
            if(train.getStation() == station) {
                for (TimetableEntity timetableEntity : train.getTimetable()) {
                    if(timetableEntity.getArrivalTime().getTime() < DateUtils.addMinutes(new Date(),
                            Integer.valueOf(generatorParametersService.findGeneratorParameterById(7).getParameterValue())).getTime()
                            && train.getState() != TrainState.DEPARTED) {
                        nearestTrains.add(train);
                    }
                }
            }
        }
        return nearestTrains;
    }
}
