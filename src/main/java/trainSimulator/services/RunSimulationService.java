package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainSimulator.models.Station;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by mitron-wojtek on 08.12.16.
 */
@Service
public class RunSimulationService {
    private static final Logger logger = Logger.getLogger(RunSimulationService.class);
    private final StationService stationService;
    private final GeneratorParametersService generatorParametersService;
    private final TrainService trainService;
    private final EventLogService eventLogService;
    private ExecutorService executorService;

    @Autowired
    public RunSimulationService(StationService stationService, GeneratorParametersService generatorParametersService, TrainService trainService, EventLogService eventLogService) {
        this.stationService = stationService;
        this.generatorParametersService = generatorParametersService;
        this.trainService = trainService;
        this.eventLogService = eventLogService;
    }

    public void runSimulation() {
        executorService = Executors.newFixedThreadPool(stationService.findAllStations().size());
        logger.info("Created executor service for simulation!");
        List<Station> allStations = stationService.findAllStations();
        for (Station station : allStations) {
            if (station.getTrainsOnStation().size() > 0) {
                Runnable simulationWorker = new SimulationService(trainService, generatorParametersService, eventLogService);
                executorService.execute(simulationWorker);
                logger.info("New instance of executor service is working!");
            }
        }
    }

    public void stopSimulation() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
            try {
                executorService.awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                logger.error("An error happened when stopping simulation: " + e.getMessage());
            }
        }
    }
}
