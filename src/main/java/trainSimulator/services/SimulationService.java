package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class SimulationService implements Runnable {
    private final GeneratorParametersService generatorParametersService;
    private final EventLogService eventLogService;
    private static Logger logger = Logger.getLogger(SimulationService.class);
    private final Object mutexObject = new Object();
    private final Station station;
    private List<Train> allTrains;
    private final Station nextStationReference;
    private final TrainService trainService;
    private boolean isRunning = true;

    @Autowired
    public SimulationService(Station station, GeneratorParametersService generatorParametersService,
                             EventLogService eventLogService, TrainService trainService, StationService stationService) {
        this.generatorParametersService = generatorParametersService;
        this.eventLogService = eventLogService;
        this.station = station;
        this.nextStationReference = stationService.findStation(station.getId() + 1);
        //Reference to next station, something like in linked list of stations
        this.trainService = trainService;
    }

    @Override
    public void run() {
        logger.info("Running simulation instance.");
        boolean runFlag = true;
        allTrains = trainService.getAllTrains();
        while (runFlag) {
            synchronized (mutexObject) {
                runFlag = isRunning;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Interrupted exception on station: " + station.getName(), e);
            }
            List<Train> nearestTrainsOnStation = getNearestTrains(allTrains);
            if (nearestTrainsOnStation != null) {
                for (Train train : nearestTrainsOnStation) {
                    if(train.getStateForStation(station) != TrainState.DEPARTED) {
                        stateMachine(train);
                    }
                }
            }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Interrupted exception while running simulation, stack: " + e.toString());
            }
        }

    private void stateMachine(Train train) {
        Date now = new Date();
        int differenceArrival = (int) ((train.getTimetable().get((int) station.getId() - 1).getArrivalTime().getTime() - now.getTime()) / 1000);
        if (differenceArrival <= Integer.valueOf(generatorParametersService.findGeneratorParameterById(5).getParameterValue()) &&
                differenceArrival > Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())
                && train.getStateForStation(station) != TrainState.ARRIVING) {
                train.setStateForStation(station, TrainState.ARRIVING);
                String logText = "Train with id: " + train.getId() + " is approaching on station " + train.getStation().getName();
                logger.info(logText);
                eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), logText));
        } else if (differenceArrival <= Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())
                && differenceArrival >= -Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())
                && train.getStateForStation(station) != TrainState.ONSTATION) {
                train.setStateForStation(station, TrainState.ONSTATION);
                String logText = "Train with id: " + train.getId() + " is on station " + train.getStation().getName();
                logger.info(logText);
                eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), logText));
        } else if (differenceArrival < -Integer.valueOf(generatorParametersService.findGeneratorParameterById(6).getParameterValue())
                && train.getStateForStation(station) != TrainState.DEPARTED) {
                if (station.getId() == (station.getRoute().getStationsOnRoute().size())) {
                    train.setStateForStation(station, TrainState.ENDED);
                    String endRouteLog = "Train with id: " + train.getId() + " ended it's route.";
                    logger.info(endRouteLog);
                    eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), endRouteLog));
                }
                train.setStateForStation(station, TrainState.DEPARTED);
                nextStationReference.getTrainsOnStation().add(train);
                String logText = "Train with id: " + train.getId() + " departed from station " + train.getStation().getName();
                logger.info(logText);
                eventLogService.createEvent(new EventLog("INFO", train.getStation().getName(), new Date(), logText));
            }
        logger.info("Simulation is running!");
    }

    private List<Train> getNearestTrains(List<Train> trains) {
        List<Train> nearestTrains = new ArrayList<>();
        for (Train train : trains) {
            for (TimetableEntity timetableEntity : train.getTimetable()) {
                if (timetableEntity.getArrivalTime().getTime() < DateUtils.addMinutes(new Date(),
                        Integer.valueOf(generatorParametersService.findGeneratorParameterById(7).getParameterValue())).getTime()
                        && train.getStateForStation(station) != TrainState.DEPARTED) {
                    nearestTrains.add(train);
                }
            }
        }
        logger.info("Got nearest trains for simulation.");
        //Easier debugging
        logger.info("On station " + station.getName() + ", trains: " + station.getTrainsOnStation());
        return nearestTrains;
    }

}
