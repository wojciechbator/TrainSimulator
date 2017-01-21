package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.*;
import trainSimulator.repositories.RoleDaoInterface;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Transactional
@Service
public class InitDatabaseService {
    private static Logger logger = Logger.getLogger(InitDatabaseService.class);
    private final EventLogService eventLogService;
    private final RouteService routeService;
    private final GeneratorParametersService generatorParametersService;
    private final UserService userService;
    private final RoleDaoInterface roleDaoInterface;

    @Autowired
    public InitDatabaseService(EventLogService eventLogService, RouteService routeService,
                               GeneratorParametersService generatorParametersService,
                               UserService userService, RoleDaoInterface roleDaoInterface) {
        this.eventLogService = eventLogService;
        this.routeService = routeService;
        this.generatorParametersService = generatorParametersService;
        this.userService = userService;
        this.roleDaoInterface = roleDaoInterface;
    }

    @PostConstruct
    public void init() {
        eventLogService.clearEvents();
        String logText = "A new session started!";
        logger.info(logText);
        eventLogService.saveEvent(new EventLog("INFO", "", new Date(), logText));
        if (roleDaoInterface.findByName("ROLE_ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleDaoInterface.update(adminRole);
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleDaoInterface.update(userRole);
            User admin = new User();
            admin.setEnabled(true);
            admin.setName("admin");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            admin.setPassword(encoder.encode("admin"));
            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);
            roles.add(userRole);
            admin.setRoles(roles);
            userService.saveUser(admin);
        }

        GeneratorParameter onStationStopTime = new GeneratorParameter();
        onStationStopTime.setParameterName("on_station_stop_time");
        onStationStopTime.setParameterValue("60");
        generatorParametersService.saveGeneratorParameter(onStationStopTime);
        GeneratorParameter departureFrequency = new GeneratorParameter();
        departureFrequency.setParameterName("departure_frequency");
        departureFrequency.setParameterValue("300");
        generatorParametersService.saveGeneratorParameter(departureFrequency);
        GeneratorParameter periodBetweenStation = new GeneratorParameter();
        periodBetweenStation.setParameterName("period_between_station");
        periodBetweenStation.setParameterValue("300");
        generatorParametersService.saveGeneratorParameter(periodBetweenStation);
        GeneratorParameter passengersCount = new GeneratorParameter();
        passengersCount.setParameterName("passengers_count");
        passengersCount.setParameterValue("50");
        generatorParametersService.saveGeneratorParameter(passengersCount);
        GeneratorParameter stateArriving = new GeneratorParameter();
        stateArriving.setParameterName("arriving_period");
        stateArriving.setParameterValue("90");
        generatorParametersService.saveGeneratorParameter(stateArriving);
        GeneratorParameter stateOnStation = new GeneratorParameter();
        stateOnStation.setParameterName("on_station_period");
        stateOnStation.setParameterValue("30");
        generatorParametersService.saveGeneratorParameter(stateOnStation);
        GeneratorParameter timeForNearestTrainMinutes = new GeneratorParameter();
        timeForNearestTrainMinutes.setParameterName("time_for_nearest_trains_minutes");
        timeForNearestTrainMinutes.setParameterValue("20");
        generatorParametersService.saveGeneratorParameter(timeForNearestTrainMinutes);
        String generatedParametersLog = "Generator parameters are set.";
        logger.info(generatedParametersLog);
        eventLogService.saveEvent(new EventLog("INFO", "", new Date(), generatedParametersLog));
        Route firstRoute = new Route();
        List<Station> stationsOnFirstRoute = new ArrayList<>();
        Route secondRoute = new Route();
        List<Station> stationsOnSecondRoute = new ArrayList<>();
        Station wroclaw = new Station();
        wroclaw.setName("Wroclaw");
        wroclaw.setIdForRoute(0);
        Station poznan = new Station();
        poznan.setName("Poznan");
        poznan.setIdForRoute(1);
        Station szczecin = new Station();
        szczecin.setName("Szczecin");
        szczecin.setIdForRoute(2);
        Station wroclaw2 = new Station();
        wroclaw2.setName("Wroclaw");
        wroclaw2.setIdForRoute(0);
        Station krakow = new Station();
        krakow.setName("Krakow");
        krakow.setIdForRoute(1);
        Station warszawa = new Station();
        warszawa.setName("Warszawa");
        warszawa.setIdForRoute(2);
        stationsOnFirstRoute.add(wroclaw);
        stationsOnFirstRoute.add(poznan);
        stationsOnFirstRoute.add(szczecin);
        firstRoute.setName("Wroclaw -> Szczecin");
        firstRoute.setStationsOnRoute(stationsOnFirstRoute);
        firstRoute.setAvailable(true);
        routeService.saveRoute(firstRoute);
        stationsOnSecondRoute.add(wroclaw2);
        stationsOnSecondRoute.add(krakow);
        stationsOnSecondRoute.add(warszawa);
        secondRoute.setName("Wroclaw -> Warszawa");
        secondRoute.setStationsOnRoute(stationsOnSecondRoute);
        secondRoute.setAvailable(true);
        routeService.saveRoute(secondRoute);
        logger.info("Init method done!");
    }
}
