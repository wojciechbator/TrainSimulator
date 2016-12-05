package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.EventLog;
import trainSimulator.models.GeneratorParameter;
import trainSimulator.models.Route;
import trainSimulator.models.Station;

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
    private final EventLogService eventLogService;
    private final StationService stationService;
    private final RouteService routeService;
    private final TimetableGeneratorService timetableGeneratorService;
    private final GeneratorParametersService generatorParametersService;

    @Autowired
    public InitDatabaseService(EventLogService eventLogService, StationService stationService, RouteService routeService,
                               TimetableGeneratorService timetableGeneratorService, GeneratorParametersService generatorParametersService) {
        this.eventLogService = eventLogService;
        this.stationService = stationService;
        this.routeService = routeService;
        this.timetableGeneratorService = timetableGeneratorService;
        this.generatorParametersService = generatorParametersService;
    }

    @PostConstruct
    public void init() {
        eventLogService.clearEvents();
        EventLog eventLog = new EventLog();
        eventLog.setComment("A new session started!");
        eventLog.setStationName("All stations active");
        eventLog.setTimestamp(new Date());
        eventLog.setType("INFO");
        eventLogService.saveEvent(eventLog);
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
        //Prepared infrastructure for train simulator, something bad happens here
        Route firstRoute = new Route();
        List<Station> stationsOnFirstRoute = new ArrayList<>();
        Route secondRoute = new Route();
        List<Station> stationsOnSecondRoute = new ArrayList<>();
        Station wroclaw = new Station();
        wroclaw.setName("Wroclaw");
        Station poznan = new Station();
        poznan.setName("Poznan");
        Station szczecin = new Station();
        szczecin.setName("Szczecin");
        Station wroclaw2 = new Station();
        wroclaw2.setName("Wroclaw");
        Station krakow = new Station();
        krakow.setName("Krakow");
        Station warszawa = new Station();
        warszawa.setName("Warszawa");
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
        stationService.saveStation(wroclaw);
        stationService.saveStation(poznan);
        stationService.saveStation(szczecin);
        stationService.saveStation(wroclaw2);
        stationService.saveStation(krakow);
        stationService.saveStation(warszawa);
    }
}
