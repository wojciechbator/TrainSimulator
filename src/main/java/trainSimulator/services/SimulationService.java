package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import trainSimulator.models.Station;
import trainSimulator.models.TimetableEntity;
import trainSimulator.models.Train;
import trainSimulator.models.TrainState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
public class SimulationService implements Runnable {
    private final StationService stationService;
    private final TrainService trainService;
    private final GeneratorParametersService generatorParametersService;
    //mutex object, for synchronizing threads
    private static Logger logger = Logger.getLogger(SimulationService.class);
    private final Object mutexObject = new Object();
    private boolean isRunning = true;
    private Station station;

    public SimulationService(StationService stationService, TrainService trainService,
                             GeneratorParametersService generatorParametersService, Station station) {
        this.stationService = stationService;
        this.trainService = trainService;
        this.generatorParametersService = generatorParametersService;
        this.station = station;
    }

    //Simulation will be in thread pool for every station, easy ExecutorService
    @Override
    public void run() {
        boolean runFlag = true;
        List<Train> allTrains = new ArrayList<>();
        while(runFlag) {
            synchronized (mutexObject) {
                runFlag = isRunning;
            }
            allTrains = trainService.getAllTrains();
            List<Train> nearestTrains = getNearestTrains(allTrains);
            if(nearestTrains != null) {
                for(Train train : nearestTrains) {
                    if(train.getState() != TrainState.CANCELLED) {
                        stateMachine(train);
                    }
                }
            }
        }
    }

    public void stateMachine(Train train) {
        Date now = new Date();
        int differenceArrival;
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
