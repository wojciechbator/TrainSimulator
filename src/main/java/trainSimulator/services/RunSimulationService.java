package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import trainSimulator.models.Station;

import java.util.concurrent.ExecutorService;

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
    public RunSimulationService(ExecutorService executorService, StationService stationService,
                                GeneratorParametersService generatorParametersService,
                                TrainService trainService, EventLogService eventLogService) {
        this.stationService = stationService;
        this.generatorParametersService = generatorParametersService;
        this.trainService = trainService;
        this.eventLogService = eventLogService;
        this.executorService = executorService;
    }

    public void runSimulation() {
            logger.info("Created executor service for simulation!");
            for (Station station : stationService.findAllStations()) {
                Runnable simulationWorker = new SimulationService(station, generatorParametersService, eventLogService, trainService, stationService);
                executorService.execute(simulationWorker);
                logger.info("New instance of executor service is working!");
            }
    }

    public void stopSimulation() {
            executorService.shutdown();
    }
}
