package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import trainSimulator.models.Route;
import trainSimulator.models.Station;

import java.util.List;

/**
 * Created by mitron-wojtek on 08.12.16.
 */
@Service
public class RunSimulationService {
    private static final Logger logger = Logger.getLogger(RunSimulationService.class);
    private final StationService stationService;
    private final GeneratorParametersService generatorParametersService;
    private final TrainService trainService;
    private final RouteService routeService;
    private final EventLogService eventLogService;
    private ThreadPoolTaskExecutor executorService;

    @Autowired
    public RunSimulationService(ThreadPoolTaskExecutor executorService, StationService stationService,
                                GeneratorParametersService generatorParametersService,
                                TrainService trainService, RouteService routeService, EventLogService eventLogService) {
        this.stationService = stationService;
        this.generatorParametersService = generatorParametersService;
        this.trainService = trainService;
        this.routeService = routeService;
        this.eventLogService = eventLogService;
        this.executorService = executorService;
    }

    public void runSimulation() {
        executorService.setCorePoolSize(stationService.findAllStations().size());
        logger.info("Created executor service for simulation!");
        List<Route> routes = routeService.getAllRoutes();
        for (Route route : routes) {
            // try to make indexing like 0 1 2 and 0 1 2 for two routes
            for (Station station : route.getStationsOnRoute()) {
                //In order to get this working I have to implement it asynchronously, all of this thread pool, so when
                //some trains arrives on the next station, the new thread invokes for this station and starts simulation
                Runnable simulationWorker = new SimulationService(station, stationService, generatorParametersService, eventLogService, trainService);
                executorService.execute(simulationWorker);
                logger.info("New instance of executor service is working!");
            }
        }
    }

    public void stopSimulation() {
            executorService.shutdown();
    }
}
