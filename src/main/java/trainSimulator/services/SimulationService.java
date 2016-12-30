package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.EventLog;
import trainSimulator.models.Station;
import trainSimulator.models.TimetableEntity;
import trainSimulator.models.Train;
import trainSimulator.utilities.TrainState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
@Transactional
public class SimulationService implements Runnable {
    private final TrainService trainService;
    private final GeneratorParametersService generatorParametersService;
    private final EventLogService eventLogService;
    private static Logger logger = Logger.getLogger(SimulationService.class);
    private final Object mutexObject = new Object();
    private final StationService stationService;
    private boolean isRunning = true;

    @Autowired
    public SimulationService(StationService stationService, TrainService trainService, GeneratorParametersService generatorParametersService,
                             EventLogService eventLogService) {
        this.trainService = trainService;
        this.generatorParametersService = generatorParametersService;
        this.eventLogService = eventLogService;
        this.stationService = stationService;
    }

    @Override
    public void run() {
        logger.info("Running simulation instance.");
        boolean runFlag = true;
        Station currentStation = stationService.findStation(0);
        List<Train> nearestTrainsOnThisStation = getNearestTrains(currentStation);
        while (runFlag) {
            synchronized (mutexObject) {
                runFlag = isRunning;
            }
            if (nearestTrainsOnThisStation != null) {
                for (Train train : nearestTrainsOnThisStation) {
                    if (train.getState() != TrainState.CANCELLED) {
                        stateMachine(train, currentStation);
                    }
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.error("Interrupted exception while running simulation, stack: " + e.toString());
            }
        }
    }

    private void stateMachine(Train train, Station station) {
        Date now = new Date();
        int differenceArrival = (int) ((train.getTimetable().get(0).getArrivalTime().getTime() - now.getTime()) / 1000);
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
                if (station.getId() < (stationService.findAllStations().size() - 1)) {
                    String previousStationName = station.getName();
                    station = stationService.getNextStation(station);
                    train.setStation(station);
                    String switchLog = "Train with id: " + train.getId() + " switched from station: " + previousStationName + " to: " + train.getStation().getName();
                    logger.info(logText);
                    eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), switchLog));
                } else {
                    train.setState(TrainState.ENDED);
                    String endRouteLog = "Train with id: " + train.getId() + " ended it's route.";
                    logger.info(endRouteLog);
                    eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), endRouteLog));
                }
            }
        }
        logger.info("Simulation is working!");
    }

    private List<Train> getNearestTrains(Station station) {
        List<Train> nearestTrains = new ArrayList<>();
        for (Train train : station.getTrainsOnStation()) {
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
