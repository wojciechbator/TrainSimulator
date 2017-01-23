package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainSimulator.models.Station;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private ExecutorService executorService = null;

    @Autowired
    public RunSimulationService(StationService stationService,
                                GeneratorParametersService generatorParametersService,
                                TrainService trainService, EventLogService eventLogService) {
        this.stationService = stationService;
        this.generatorParametersService = generatorParametersService;
        this.trainService = trainService;
        this.eventLogService = eventLogService;
    }

    public void runSimulation() {
            logger.info("Created executor service for simulation!");
            executorService = Executors.newFixedThreadPool(stationService.findAllStations().size());
            for (Station station : stationService.findAllStations()) {
                Runnable simulationWorker = new SimulationService(station, generatorParametersService, eventLogService, trainService);
                executorService.execute(simulationWorker);
                logger.info("New instance of executor service is working!");
            }
    }

    public void stopSimulation() {
            executorService.shutdown();
    }
}
